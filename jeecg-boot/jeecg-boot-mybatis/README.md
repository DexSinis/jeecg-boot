#cjw_admin 
配置文件  
quartz.properties  定时任务数据库配置
logback.xml logback配置文件  
generatorConfig.xml 生成器配置文件  
application-dev.properties 开发环境配置文件  
application-pro.properties 生产环境配置文件  
value.properties 自定义参数配置文件   

####swagger-ui url  
[swagger-ui url](http://localhost:9001/swagger-ui.html#/)
####druid url  
[druid-ui url](http://127.0.0.1:9001/druid/index.html) 
root/tiger  

###目录说明
org.jeecg 根路径   
aop aop相关功能,获取参数调用module里的service的实现  
cache 缓存相关功能  
config 模块配置  
dao 数据库查询相关功能  
dict 字典变量  
dto 数据传输bean  
entity 数据库表对应实体对象  
exception 自定义错误对象    
interceptor 拦截器实现,获取参数调用module里的service的实现  
module 业务功能模块实现,业务逻辑写在这里  
util 工具类  
websocket websocket功能实现,获取参数调用module里的service的实现  

config/druid 数据库连接池配置  
config/event 事件配置  
config/interceptor 拦截器配置  
config/mybatis mybatis相关配置  
config/quartz 定时任务配置  
config/rest http请求工具配置  
config/session session保存到redis配置  
config/shiro shiro配置  
config/swagger 配置  
config/websocket 配置  

resource/static  静态资源目录  
[示例:](http://127.0.0.1:9001/index.html)


###测试
get请求测试✔  
post请求测试✔  
swagger测试✔  
druid监控测试✔  
mybatis CURD测试✔  
mybatis 分页插件使用测试✔  
mybatis generator 代码生成器测试✔  
redis CURD测试✔  
quartz 定时任务接口测试✔  
获取配置文件参数测试✔  
系统启动事件测试✔  
websocket测试ws://localhost:9001/websocket/1-----测试工具http://coolaf.com/tool/chattest✔  
静态资源访问测试✔  
日志记录到文件测试✔  
日志记录到数据库测试✔  
统一接口报错返回信息测试✔  
junit测试✔
springbootjunit 测试✔  
spring事务测试✔  
打包测试✔
```
mvn clean package
```
运行包测试✔
```
指定环境
java -jar yewyw-mybatis.jar --spring.profiles.active=dev
java -jar yewyw-mybatis.jar --spring.profiles.active=pro
linux部署命令 指定环境 重定向日志 后台启动
java -jar yewyw-mybatis.jar --spring.profiles.active=pro >yewyw-mybatis.log 2>&1 &
```


###idea spirng boot devtools失效解决  
[idea spirng boot devtools失效解决](https://blog.csdn.net/moshowgame/article/details/81129860)  

###lombok插件安装  
[lombok插件安装](https://blog.csdn.net/wochunyang/article/details/81736354)








