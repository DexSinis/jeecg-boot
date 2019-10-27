echo "删除当前占用的端口----->"
kill -9 $(lsof -i:9001 -t)
echo "启动测试环境yewywy-admin：----->dev"
java -jar yewyw-interface.jar --spring.profiles.active=dev
echo "启动服务成功！"
