#!/bin/bash

#export JAVA_HOME=/usr/local/lib64/jdk1.8.0_144/

#export PATH=$JAVA_HOME/bin:$PATH

#export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

source /etc/profile

#判断tomcat是否启动，启动就先关闭
PIDS=`ps -ef |grep yewyw-interface.jar |grep -v grep | awk '{print $2}'`

if [ "$PIDS" != "" ]; then
        echo "tomcat is runing!"
        #杀掉进程
        kill -9 $PIDS
        echo "tomcat is killed!"
else
        echo "tomcat is not runing!"
fi
nohup java -jar /data/j1/yewyw-interface.jar --spring.profiles.active=dev >/data/j1/yewyw-interface.log 2>&1 &
cd /root
