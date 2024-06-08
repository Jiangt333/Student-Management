# 后端本地测试
（1）java 版本为 1.8，maven版本为3.8.1
（2）在mysql中创建stums库，修改eureka，gateway-service 和其他sevice项目中的resource/application-dev.yml和resource/application-dev.yml中的username和password【不要push】
（3）依次运行eureka，gateway-service 和其他sevice项目

# 后端自动部署
* 云服务器上配置git
* 在云服务器上配置好maven、java环境
* 本地用maven打好jar包后压缩项目为zip，文件传输到云服务器上
* 云服务器上`unzip stuMS.zip`命令解压缩
* 解压缩，进入到有docker-compose的目录
*   先`docker-compose down`，down掉之前起的
*   再删除镜像 `docker rmi $(docker images | grep "stums-")`
*   再分别给环境依赖和服务打镜像：
`docker-compose -f docker-compose-env.yml build`
`docker-compose -f docker-compose.yml build`
*   先启动环境依赖 `docker-compose -f docker-compose-env.yml up -d`
*   再启动各个服务 `docker-compose -f docker-compose.yml up -d`

