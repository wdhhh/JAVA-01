### 第九课

##### 1.（选做）使 Java 里的动态代理，实现一个简单的 AOP。 

[实现动态代理](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/%E7%AC%AC%E4%B9%9D%E8%AF%BE/springWork/src/main/java/proxy/ProxyTest.java)

##### 2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）,提交到 Github。

[Bean的装配](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/%E7%AC%AC%E4%B9%9D%E8%AF%BE/springWork/src/main/java/bean/BeanTest.java)

### 第十课



##### 3.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

1. pom文件加依赖；

   [3.1-pom](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/%E7%AC%AC%E5%8D%81%E8%8A%82%E8%AF%BE/work10/pom.xml)

2. resource/META-INF下创建文件spring.factories，配置要加载到容器中的类；

   [3.2-factories](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/%E7%AC%AC%E5%8D%81%E8%8A%82%E8%AF%BE/work10/src/main/resources/META-INF/spring.factories)

3. 创建配置文件application.yml；

   [3.3application.yml](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/%E7%AC%AC%E5%8D%81%E8%8A%82%E8%AF%BE/work10/src/main/resources/application.yml)

4. 在启动类加上@SpringBootApplication；

   [3-启动类url](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/第十节课/work10/src/main/java/work/starter/StarterApplication.java)



##### 6.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：

1）使用 JDBC 原生接口，实现数据库的增删改查操作。

1. 加载注册驱动；
2. 获取connection
3. 获取statement
4. 执行sql
5. 关闭资源

[6.1-url](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/%E7%AC%AC%E5%8D%81%E8%8A%82%E8%AF%BE/work10/src/main/java/work/jdbc/JDBCUtil01.java)

2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。

[6.2-url](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/%E7%AC%AC%E5%8D%81%E8%8A%82%E8%AF%BE/work10/src/main/java/work/jdbc/JDBCUtil02.java)

3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。

[6.3application.yml](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/%E7%AC%AC%E5%8D%81%E8%8A%82%E8%AF%BE/work10/src/main/resources/application.yml)

[6.3使用连接池的增删改查](https://github.com/wdhhh/JAVA-01/blob/main/Week_05/%E7%AC%AC%E5%8D%81%E8%8A%82%E8%AF%BE/work10/src/main/java/work/starter/service/StudentHikariService.java)