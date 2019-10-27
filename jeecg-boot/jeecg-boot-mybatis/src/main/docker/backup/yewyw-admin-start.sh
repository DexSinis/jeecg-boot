# startup.sh 启动项目
#!/bin/sh
echo "授予当前用户权限"
chmod 777 /data/j1/yewyw-admin.jar
echo "执行....."
java -jar /data/j1/yewyw-admin.jar --spring.profiles.active=dev > /data/j1/yewyw-admin.log & 
echo "退出日志" 
