# nest - 伊阳静美。

---

本项目来自于开源项目 ： [lovers-website](https://github.com/katanala/lovers-website)

 项目进行了大量的优化，遵循阿里巴巴开发手册;
 所有service 进行抽象化，增加本地存储功能，目前项目正在开发中。。。

## 简介

- 目标（goal）：部署简单，安全高效，只需修改配置，即可搭建自己的情侣网站，属于你们的小窝。
- http 地址（address of http ）：http://www.yiyjm.com
- https地址（address of https）：https://www.yiyjm.com

语言（language） ：java

JDK 版本：11.0.6

数据库（database）：mysql 8

框架（framework）： SpringBoot2，SpringMVC，Mybatis，Freemarker，Websocket。

集成开发环境（IDE）：IDEA(2020.1)

模块（modular）：聊天（chat），博客（blog），留言（message），相册（album），故事（story），ip 统计（ip statistics），邮件通知（mail notification）....

---

### 部署说明

数据库sql：src\main\java\com\yiyjm\nest\config\nest.sql

密码、密钥等配置：src\main\java\com\yiyjm\nest\config\ConfigI.java

数据库、邮箱等配置：src\main\resources\application.yml

---

### 运行说明


问：如何运行？

答：运行前请确保配置文件和数据库配置正确，运行入口类中的main方法，src\main\java\com\yiyjm\nest\NestApplication.java
   该文件中也有端口配置 ，需要注意。


问：如何访问？

答：默认以https方式访问，浏览器输入地址：https://127.0.0.1


问：如何使用自己的域名SSL证书？

答：需要将 src\main\resources\www.yiyjm.com.jks 换成自己域名的SSL证书，
同时修改 src\main\resources\application.yml 中的 server: ssl: key-* 的参数值
