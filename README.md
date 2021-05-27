##本项目采用spring-cloud和alibaba-spring-cloud结合
```使用shiro作为鉴权框架
``本项目默认采用alibaba-nacos做注册
运行项目需要redis
后台模版框架thymeleaf
数据库自定

本项目只提供了简易，登陆。具体业务需要自己实现
项目结构：
feng-cloud-admin
   ├── feng-cloud-admin-dual   //存放do、dao、mapper
   ├── feng-cloud-admin-feign  //feign
   └── feng-cloud-admin-web    //服务

使用archetype快速构建
step：
1。进入feng-cloud-admin根目录执行 mvn archetype:create-from-project
2。进入 ${projectPath}/target/generated-sources/archetype中
3。安装 mvn clean install
4。构建mvn的archetype-catalog文件，执行mvn clean install
5。进入需要安装的目录执行安装  例如mvn archetype:generate -DarchetypeGroupId=com.github.fengzai -DarchetypeArtifactId=feng-cloud-admin-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DinteractiveMode=false -DarchetypeCatalog=local -Dversion=1.0.0-SNAPSHOT -DgroupId=com.github.fengzai -DartifactId=feng-cloud-mall
项目中使使用了springcl