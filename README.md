AppServer

## 简介
AppServer 主要是为手机app客户端提供后台服务支持。

## 模块

- redis查询  
通过jedis查询redis-server，然后以JSON返回。

- mysql查询
通过JDBC查询mysql-server，然后以JSON返回。

## 难点

- linux上tomcat发布。主要是如何边修改边发布？  
目前想到的解决方案是 把windows上生成的archive包上传到Github，
然后在下载到linux服务器的tomcat/webapps中。

- 手机App使用OKhttp 进行web访问，基于Restful格式。

## 服务器信息
阿里云管理：https://signin.aliyun.com/1687334153864864/login.htm  
用户名:liuyang@rlb  
网页远程密码：135246  
服务器登录密码：瑞莱宝@1234

## 服务器搭建
1. 安装redis，配置自启动  
2. 安装jdk
3. 安装tomcat7