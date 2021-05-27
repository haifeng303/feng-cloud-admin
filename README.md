##本项目采用spring-cloud和alibaba-spring-cloud结合
```
feng-cloud-admin
├── feng-cloud-admin-dual   //存放do、dao、mapper
├── feng-cloud-admin-feign  //feign接口简单校验，防止api被直接访问，使用@EnableFeignSecurity注解加config.feign.patterns配置，也可使用oauth2做校验
├── feng-cloud-admin-web    //服务
├── apps                       //日志文件
└── sql                        //sql
````
本项目默认采用alibaba-nacos做注册
运行项目需要redis

feng-cloud-admin 可以使用archetype重复安装
````
step：
    1。进入feng-cloud-admin根目录执行 mvn archetype:create-from-project 
    2。进入 ${projectPath}/target/generated-sources/archetype中
    3。安装 mvn clean install
    4。构建mvn的archetype-catalog文件，执行mvn clean install
    5。进入需要安装的目录执行安装  例如mvn archetype:generate -DarchetypeGroupId=io.github.haifeng303 -DarchetypeArtifactId=feng-cloud-admin-archetype -DarchetypeVersion=1.0.0-SNAPSHOT -DinteractiveMode=false -DarchetypeCatalog=local -Dversion=1.0.0-SNAPSHOT -DgroupId=io.github.haifeng303 -DartifactId=feng-cloud-mall