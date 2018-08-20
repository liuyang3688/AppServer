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

- redis 设计
 key：两位类型-三位id
 value: HashMap<参数id, 参数值>

如果取所有类型数据，则使用jedis.keys("*"), 只要键符合格式，则取值。
如果取某一类型数据，则使用jedis.keys("lx-*"), 只要键符合格式，则取值。
如果取某一设备数据，则使用jedis.hgetall("lx-id") 即可。

## 服务器信息
阿里云管理：https://signin.aliyun.com/1687334153864864/login.htm  
用户名:liuyang@rlb  
网页远程密码：135246  
服务器登录密码：瑞莱宝@1234

## 服务器搭建
1. 安装redis，配置自启动  
2. ~~安装jdk~~
3. ~~安装tomcat7~~
4. 集成redis
5. app访问redis
6. app界面设计
7. app访问mysql

## 阿里云服务器端口配置
1. 配置安全组（管理网页）
2. 配置防火墙（服务器）
3. 开启ping
入站规则-文件和打印机共享（回显请求-ICMPV4-In）
4. 开启Telnet
首先，需要安装Telnet服务。
然后，启动Telnet服务。（安装后，默认不启动）
然后，配置安全组。
然后，配置防火墙。