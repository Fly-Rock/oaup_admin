#!usr/bin/env bash

export LC_ALL=en_US.UTF-8
ulimit -c unlimited

[ -z $JAVA_HOME ] && export JAVA_HOME=/home/soft/jdk1.8.0_66

PRODUCTION=${PRODUCTION:-false}
JAVA_CMD="${JAVA_HOME}/bin/java"
PROFILES="${PROFILES:-dev}"

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"


