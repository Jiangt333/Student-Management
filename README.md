# 后端本地测试
（1）java 版本为 1.8，maven版本为3.8.1
（2）在mysql中创建stums库，修改eureka，gateway-service 和其他sevice项目中的resource/application-dev.yml和resource/application-dev.yml中的username和password【不要push】
（3）依次运行eureka，gateway-service 和其他sevice项目

# 后端自动部署
* 云服务器上配置git
* 在云服务器上配置好maven、java环境
* 在云服务器上pull该项目
* maven打包该项目：
