#!/bin/sh

script_path=$(cd "$(dirname "$0")"; pwd)
base=$(dirname ${script_path})
$base/bin/daemon.sh stop
