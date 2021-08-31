#!/bin/sh
#. /etc/init.d/functions

JAVA_OPT=$2
CLASS_PATH=$3
SERVICE_NAME=$4
ENTRY_POINT=$5
SCRIPT_PATH=$(cd "$(dirname "$0")"; pwd)
BASE=$(dirname ${SCRIPT_PATH})
SERVICE_PID=${SERVICE_NAME}.pid
PID_PATH_NAME=${BASE}/pids/$SERVICE_PID
echo $CLASS_PATH
echo $ENTRY_POINT

# Start service
start() {
    echo "Starting $SERVICE_NAME ..."

    if [ -f "$PID_PATH_NAME" ]; then
    	PID=$(cat $PID_PATH_NAME);
    else
	    PID=0;
    fi
    if [ ! -d "/proc/$PID" ]; then
       # nohup java $JAVA_OPT -Xmx512M -Xms512M -classpath $CLASS_PATH $ENTRY_POINT > $BASE/logs/log.log &
        nohup java $JAVA_OPT -Xmx512M -Xms512M -classpath $CLASS_PATH $ENTRY_POINT > /dev/null 2>&1 &
        pid=$!
        sleep 1

        if [ ! -d "/proc/$pid" ]; then
            echo "$SERVICE_NAME not started. See log for detail."
            rm -f $PID_PATH_NAME
            rm -f /var/lock/subsys/$SERVICE_NAME
            exit 1
        else
            echo $pid > $PID_PATH_NAME
            echo "$SERVICE_NAME started ... PID: $!"
            touch /var/lock/subsys/$SERVICE_NAME
        fi
    else
        echo "$SERVICE_NAME is already running ..."
    fi
}

# Stop service
stop() {     
    PID_DIR_NAME=${BASE}/pids 
    for file in $(ls $PID_DIR_NAME)
    do 
     if [ ! -d $file ]
     then 
      SERVICE_NAME=${file%.*}
      PID_PATH_NAME=$PID_DIR_NAME/$file
     fi
    done
     
    if [ -f "$PID_PATH_NAME" ]; then
    	PID=$(cat $PID_PATH_NAME);
    else
	    PID=0;
    fi

     if [ ! -n "$SERVICE_NAME" ]; then
      SERVICE_NAME=' The service'
     fi
  
    if [ -d "/proc/$PID" ]; then
        echo "$SERVICE_NAME stoping ..."
        for p_pid in `ps -ef |grep $PID|egrep -v grep | awk '{print $2}'`
        do
         kill -s 9 $p_pid
        done
        echo "$SERVICE_NAME stopped ..."
        rm -f $PID_PATH_NAME
        rm -f /var/lock/subsys/$SERVICE_NAME
    else
        echo "$SERVICE_NAME is not running."
    fi
}

status() {
    if [ -f "$PID_PATH_NAME" ]; then
    	PID=$(cat $PID_PATH_NAME);
    else
	    PID=0;
    fi
    #echo $PID;

    if [ -d "/proc/$PID" ]; then
        echo "Service '$SERVICE_NAME' is running..."
	echo "PID: $PID"
    else
        echo "Service '$SERVICE_NAME' is not running."
    fi
}

case $1 in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        start
        ;;
    status)
        status
        ;;
    *)
        echo $"Usage: $0 {start|stop|restart|reload|status}"
        exit 1
esac

exit 0
