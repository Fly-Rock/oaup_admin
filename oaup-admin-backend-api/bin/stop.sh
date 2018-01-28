#!/usr/bin/env bash
set -f

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

$SCRIPT_DIR/service.sh stop
