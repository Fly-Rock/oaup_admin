#!/usr/bin/env bash

# -----------------------------------------------------------------------------
# Control Script for dubbo service
#
# Environment Variable Prerequisites:
#
#   JAVA_HOME
#   JAVA_OPTS       (Optional)
#   PROPERTIES_FILE (Default: ../conf/app.properties)
#   LOG_DIR         (Default: ../logs)
#   LOG_LEVEL       (Default: INFO)
#   CONF_DIR        (Default: ../conf)
#   PID_FILE        (Default: ./bin/.service_pid)
#
#   DEBUG_OPTS      (Optional) only works in dev mode
#   JMX_OPTS        (Optional)
#
# Author: gc@hujiang.com
# -----------------------------------------------------------------------------

set -f

PROG_NAME=$0
ACTION=$1
TOP_PID=$$


function usage() {
  echo "Usage: $PROG_NAME"
  echo "
  start       : start service in a separate window
  stop        : stop service
  restart     : restart service
  run         : start service in the current window(for dev)
  debug       : start service in a debugger

  status      : get dubbo service status
  list        : get dubbo service list
  conns       : get dubbo service connections
  verbose     : get dubbo service status verbose

  datasource  : get datasource
  sql         : get sql list
  selconns    : get active sql
  sqlerror    : get sql error list
  sqldetail   : get sql detail. eg: ./service.sh sqldetail 101

  pid         : get process id
  info        : get service info
  cksum       : display service checksums
  sysprops    : get jvm system properties
  threads     : get user threads name(without stacktrace)
  jstack      : run jstack, output to stdout
  series      : jstack series, output to special logfile
  busy        : get busiest thread id and stack trace
  dump        : dump to logfile, includes threads/heap-histo/connections, etc.
  heapdump    : live heap dump, use with caution!"

  exit 1
}

trap "exit 1" TERM
function quit() {
  local msg=$1
  [ ! -z "$msg" ] && echo $msg >&2
  kill -s TERM $TOP_PID
}

if [ $# -lt 1 ]; then
    usage
fi

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
SERVICE_HOME="$( dirname $SCRIPT_DIR )"
PID_FILE=$SERVICE_HOME/bin/.pid_file

source "$SCRIPT_DIR/disconf.sh"
source "$SCRIPT_DIR/setenv.sh"
source "$SCRIPT_DIR/hook.sh"
APP_MODEL=`ls -1 $SERVICE_HOME/lib | grep '.jar' | head -n1`

function check_service_pid() {

  if [ -z "$PID_FILE" ] || [ ! -f "$PID_FILE" ]; then
    return 0 #var empty or file not exists
  fi

  if [ ! -s "$PID_FILE" ]; then # empty file
    rm -f "$PID_FILE" >/dev/null || exit 1
    return 0
  fi

  [ ! -r "$PID_FILE" ] && echo "Unable to read PID file. Start aborted." && exit 1

  echo "Existing PID file found during start."
  local pid=`cat "$PID_FILE"`
  ps -p $pid >/dev/null 2>&1
  if [ $? -eq 0 ] ; then
    echo "Service appears to still be running with PID $pid. Start aborted." && exit 1
  fi

  echo "Removing/clearing stale PID file."
  rm -f "$PID_FILE" >/dev/null || exit 1
}

function prepare_start() {
  check_service_pid
  prepare_opts $@
  [ ! -d $DEFAULT_LOG_DIR ] && mkdir -p $DEFAULT_LOG_DIR
  export CLASS_PATH="$CONF_DIR:$SERVICE_HOME/lib/*"
}

function prepare_opts() {
  while (($#)); do
    if [ $1 == "-jmx" ];then
      JMX_OPTS="$JMX_OPTS -Djava.rmi.server.hostname=$(get_default_ip)"
      JMX_OPTS="$JMX_OPTS -Dcom.sun.management.jmxremote.port=$(get_jmx_port)"
      export JAVA_OPTS="$JAVA_OPTS $JMX_OPTS"
    elif [ $1 == "-jfr" ];then
      export JAVA_OPTS="$JAVA_OPTS $JFR_OPTS" 
    else
      export JAVA_OPTS="$JAVA_OPTS $1"
    fi
    shift
  done
}

function start() {
  prepare_start $@

  beforeStart
  nohup $JAVA_CMD $JAVA_OPTS -jar $SERVICE_HOME/lib/$APP_MODEL >> $SERVICE_HOME/stdout.log  2>&1 &  
  local pid="$!"
  sleep 1
  if kill -0 "$pid" >/dev/null 2>&1; then
    echo $pid > "$PID_FILE"
    echo "service started, java pid: $pid"
  else
    echo "start failed" 
  fi
  afterStart
}

function run() {
  if [[ "$@" == *-jmx* ]];then
    prepare_start $@
  else
    prepare_start -jmx $@
  fi

  beforeStart
  JAVA_OPTS="$JAVA_OPTS $JFR_OPTS -Dapp.run.mode=dev"
  # in current window
  "$JAVA_CMD" $JAVA_OPTS -jar "$SERVICE_HOME/lib/$APP_MODEL"
}

function debug() {
  export JAVA_OPTS="$JAVA_OPTS $DEBUG_OPTS"
  run $@
}

function stop() {
  if [ -f "$PID_FILE" ]; then
    if [ -s "$PID_FILE" ]; then
      if [ -r "$PID_FILE" ]; then
        local pid=$(cat $PID_FILE)
        if kill -0 "$pid" >/dev/null 2>&1; then
          inner_stop $pid && return 0
        else
          rm -f "$PID_FILE" && return 0
        fi
      fi
    fi
  fi

  inner_stop
}

function inner_stop() {
  local pid=""
  if [ ! -z "$1" ];then
    pid=$1
  else
    pid=$( get_service_pid )
  fi

  [ -z "$pid" ] && return 0

  kill -0 "$pid" >/dev/null 2>&1
  [ $? -gt 0 ] && return 0

  beforeStop
  local sleepTime=5
  ## 线程kill掉以后，rm掉 进程文件
  kill $pid
  [ $? -eq 0 ] && rm -f "$PID_FILE"

  while [ $sleepTime -ge 0 ]; do
    kill  "$pid" >/dev/null 2>&1
    if [ $? -gt 0 ];then
	[ -f "$PID_FILE" ] && rm -f "$PID_FILE"
	echo "应用程序正常退出..."
    	return 0
    fi

    if [ $sleepTime -gt 0 ];then
        sleep 1 && echo -n "."
        let sleepTime--
    else
        echo "Service did not stop in time."
        echo "To aid diagnostics a thread dump has been written to standard out."
	echo "kill -9 "$pid
        kill -9 $pid
    fi
  done
  afterStop
}


function pid() {
  local servicePid=$( get_service_pid )
  [ -z "$servicePid" ] && quit "Service is not running"
  echo $servicePid
}

function get_service_pid() {
  ps x | grep java | grep -v grep | grep "$APP_MODEL" | awk '{print $1}' 
}

function is_available_port() {
  local port=$1
  if [[ "$OSTYPE" = *linux* ]];then
    r=$( netstat -ant | awk '$6=="LISTEN" && $4~":'$port'$"' )
  elif [[ "$OSTYPE" = *darwin* ]];then
    r=$( netstat -ant | awk '$6=="LISTEN"' | grep "\.$port " )
  else
    quit "unknown system"
  fi

  if [ -z "$r" ];then
    echo "yes" # available
  else
    echo "no" # port has been used
  fi
}

function get_jmx_port() {
  if [ $(is_available_port $JMX_PORT) = "yes" ];then
    echo $JMX_PORT && return
  fi

  for port in `seq 9999 10000 49999`;do
    if [ $(is_available_port $port) = "yes" ];then
      echo $port && return
    fi
  done

  for port in `seq 50999 1000 59999`;do
    if [ $(is_available_port $port) = "yes" ];then
      echo $port && return
    fi
  done

  quit "cannot found available jmx port"
}

# for pipeline
function trim() {
  read str;
  echo `trim_str "$str"`;
}

function trim_str() {
  local str=$1
  shopt -s extglob
  str="${str##*( )}"
  str="${str%%*( )}"
  shopt -u extglob
  echo $str
}

function dubbo() {
  local invoke="$1"
  kill -0 $(pid) >/dev/null 2>&1
  if [ ! -z "$SERVICE_PORT" ];then
    echo "$invoke" | nc -i 1 127.0.0.1 $SERVICE_PORT
  else
    echo "Service port unknown"
    exit 1
  fi
}

function status() {
  dubbo "--no-prompt status" 
}

function verbose() {
  dubbo "--no-prompt status -l" 
}

function list() {
  dubbo "--no-prompt ls" 
}

function connections() {
  dubbo "--no-prompt ps -l $SERVICE_PORT" 
}

function druid() {
  local pid=$(pid)
  local args=$@
  local main="com.alibaba.druid.support.console.DruidStat"
  local jar=`ls $SERVICE_HOME/lib | grep druid`
  local classpath="$SERVICE_HOME/lib/$jar:$JAVA_HOME/lib/tools.jar"
  "$JAVA_CMD" -cp "$classpath" $main $args $pid
}

function druid_ds() {
  druid -ds $@
}

function druid_ds_detail() {
  local dsId=$1
  druid -ds -id $dsId -detail
}

function druid_sql() {
  druid -sql
}

function druid_sql_detail() {
  local sqlId=$1
  druid -sql -id $sqlId -detail | grep -Ev "\-INFO|logback" | cut -c-120 | sed '/LastErrorStackTrace/,/LastErrorTime/{//!d}'
}

function druid_error_list() {
  druid -sql | awk -F'|' '$8>0 || $0~/+/'
}

function druid_act() {
  druid -act
}

function info() {
  local pid=$(pid)
  local jmx_addr=$(get_jmx_addr)
  echo "service running, pid: $pid"
  echo "service cksum: $(check_sum $SERVICE_HOME/lib)"
  echo "service name: $SERVICE_NAME"
  echo "service port: $SERVICE_PORT"
  [ ! -z "$jmx_addr" ] && echo "jmx addr: $jmx_addr"

  print_elapsed_time $pid
  print_threads_number $pid
  echo "ip list: $(ip_list)"
}

function print_elapsed_time() {
  local pid=$1
  local elapsed=$(ps -oetime= -p $pid | trim)
  local started=$(ps -olstart= -p $pid | trim)
  if [[ $OSTYPE == *linux* ]]; then
    started=$(date +'%Y-%m-%d %H:%M:%S' -d "$started")
  fi
  local cpu_time=$(ps -otime= -p $pid | trim)
  echo "started from: $started, elapsed: $elapsed, cumulative cpu time: $cpu_time"
}

function print_threads_number() {
  local pid=$1
  local n1=$(get_threads_number $pid)
  local n2=$(get_dubbo_threads_number $pid)
  echo "java threads: $n1, dubbo handler threads: $n2"
}

function get_jmx_addr() {
  local pid=$(pid)
  local host=$(ps -f -p$pid | tr ' ' '\n' | grep "java.rmi.server.hostname" | cut -d= -f2)
  local port=$(ps -f -p$pid | tr ' ' '\n' | grep "com.sun.management.jmxremote.port" | cut -d= -f2)
  [ ! -z "$host" ] && [ ! -z "$port" ] && echo "$host:$port"
}

function system_properties() {
  local pid=$(pid)
  jinfo -sysprops $pid
}

function get_default_ip() {
  local ip=$( /sbin/ifconfig | grep "inet " | awk '{print $2}' | sed 's/addr://' | grep "$NETWORK" )
  [ ! -z "$ip" ] && echo $ip && return
  /sbin/ifconfig | grep "inet " | awk '{print $2}' | sed 's/addr://' | grep -v "127.0.0.1" | head -1
}

function ip_list() {
  if [ -r /sbin/ip ]; then
    local list=$(/sbin/ip -o -4 addr list | awk '{print $4}' | cut -d'/' -f1 | tr '\n' ',')
    echo ${list%,}
  else
    local list=$(/sbin/ifconfig | grep "inet " | awk '{print $2}' | sed 's/addr://' | tr '\n' ',')
    echo ${list%,}
  fi
}

function jstack() {
  local pid=$(pid)
  "$JAVA_HOME"/bin/jstack $pid
}

function jstack_series() {
  local pid=$(pid)
  local count=${1:-5}  # defaults 5 times
  local delay=${2:-0.5} # defaults 0.5 seconds

  while [ $count -gt 0 ]; do
    "$JAVA_HOME"/bin/jstack $pid > $LOG_DIR/jstack.$pid.$(date +%H%M%S.%N)
    sleep $delay
    let count--
    echo -n "."
  done
}

function get_threads() {
  local pid=$(pid)
  local vm_threads="GC task|VM |CompilerThread|Finalizer|Reference Handler|Signal Dispatcher"
  "$JAVA_HOME"/bin/jstack $pid | grep "^\"" | grep -Ev "$vm_threads"
}

function get_threads_number() {
  local pid=$1
  if [ $(uname) = "Linux" ];then
    ps -eL | grep $pid | wc -l
  else
    "$JAVA_HOME"/bin/jstack $pid | grep "^\"" | wc -l
  fi
}

function get_dubbo_threads_number() {
  local pid=$1
  "$JAVA_HOME"/bin/jstack $pid | grep "^\"DubboServerHandler-" | wc -l
}

function get_busy_thread() {
  if [ $(uname) = "Linux" ];then
    local pid=$(pid)
    local line=`top -H -b -n 1  -p $pid | sed '1,/^$/d' | sed '1d;/^$/d' | grep -v $pid | sort -nrk9 | head -1`
    echo "$line" | awk '{print "tid: "$1," cpu: %"$9}'
    local tid=`printf "%0x" $(echo "$line" | awk '{print $1}')`
    "$JAVA_HOME"/bin/jstack $pid | grep $tid -A20 | sed -n '1,/^$/p'
  else 
    echo "only works in linux"
    exit 1
  fi
}

function heapdump() {
  local pid=$(pid)
  "$JAVA_HOME"/bin/jmap -dump:live,format=b,file="$LOG_DIR/heap-$pid.bin" $pid
  echo "dump file: $LOG_DIR/heap-$pid.bin"
}

function dump() {
  local pid=$(pid)
  local dump_dir="$LOG_DIR/dump"
  local dump_date=$( date +%Y%m%d%H%M%S )
  local date_dir="$dump_dir"/"$dump_date"
  [ ! -d "$date_dir" ] && mkdir -p "$date_dir"

  "$JAVA_HOME"/bin/jstack $pid > $date_dir/jstack-$pid.dump 2>&1 && echo -e ".\c"
  "$JAVA_HOME"/bin/jinfo $pid > $date_dir/jinfo-$pid.dump 2>&1 && echo -e ".\c"
  "$JAVA_HOME"/bin/jstat -gcutil $pid > $date_dir/jstat-gcutil-$pid.dump 2>&1 && echo -e ".\c"
  "$JAVA_HOME"/bin/jstat -gccapacity $pid > $date_dir/jstat-gccapacity-$pid.dump 2>&1 && echo -e ".\c"
  "$JAVA_HOME"/bin/jmap $pid > $date_dir/jmap-$pid.dump 2>&1 && echo -e ".\c"
  "$JAVA_HOME"/bin/jmap -heap $pid > $date_dir/jmap-heap-$pid.dump 2>&1 && echo -e ".\c"
  "$JAVA_HOME"/bin/jmap -histo $pid > $date_dir/jmap-histo-$pid.dump 2>&1 && echo -e ".\c"

  [ -x /usr/sbin/lsof ] && /usr/sbin/lsof -p $pid > $date_dir/lsof-$pid.dump && echo -e ".\c"
  [ -x /bin/netstat ] && /bin/netstat -an  > $date_dir/netstat.dump 2>&1 && echo -e ".\c"
  [ -x /usr/bin/iostat ] && /usr/bin/iostat > $date_dir/iostat.dump 2>&1 && echo -e ".\c"
  [ -x /usr/bin/mpstat ] && /usr/bin/mpstat > $date_dir/mpstat.dump 2>&1 && echo -e ".\c"
  [ -x /usr/bin/vmstat ] && /usr/bin/vmstat > $date_dir/vmstat.dump 2>&1 && echo -e ".\c"
  [ -x /usr/bin/free ] && /usr/bin/free -t > $date_dir/free.dump 2>&1 && echo -e ".\c"
  [ -x /usr/bin/sar ] && /usr/bin/sar > $date_dir/sar.dump 2>&1 && echo -e ".\c"
  [ -x /usr/bin/uptime ] && /usr/bin/uptime > $date_dir/uptime.dump 2>&1 && echo -e ".\c"

  echo "DUMP: $date_dir"
}

function check_sum () {
  local dir=$1
  local dirsum=0
  for sum  in $(find ${dir} -type f | xargs cksum | awk '{print $1}')
  do
    dirsum=$(( ${sum} + ${dirsum} ))
  done
  echo ${dirsum}
}

case "$ACTION" in
    pid)
        pid
    ;;
    start)
        shift && start $@
    ;;
    run)
        shift && run $@
    ;;
    debug)
        shift && debug $@
    ;;
    stop)
        stop
    ;;
    status)
        status 
    ;;
    list)
        list 
    ;;
    info)
        info
    ;;
    sysprops)
        system_properties
    ;;
    conns)
        connections
    ;;
    verbose)
        verbose
    ;;
    dump)
       dump 
    ;;
    heapdump)
       heapdump 
    ;;
    datasource)
       druid_ds
    ;;
    sql)
       druid_sql
    ;;
    sqlconns)
       druid_act
    ;;
    sqlerror)
       druid_error_list
    ;;  
    sqldetail)
       shift && druid_sql_detail $@
    ;;  
    busy)
       get_busy_thread
    ;;
    threads)
       get_threads
    ;;
    jstack)
       jstack
    ;;
    series)
       shift && jstack_series $@
    ;;
    restart)
        restart
    ;;
    cksum)
        check_sum "$SERVICE_HOME/lib"
    ;;
    *)
        usage
    ;;
esac
