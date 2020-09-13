## 回顾

```java
拦截器: SpringMVC提供的技术
    过滤器和拦截器:
		过滤器是在servlet执行前和执行完毕后进行过滤处理
        在Servlet执行后拦截,拦截器在处理器执行前后拦截,视图解析器执行完毕后拦截
统一异常处理机制: SpringMVC
    使用SpringMVC框架后,所有的异常都不需要自己处理了,遇到异常直接向上抛即可,抛给SpringMVC框架,框架会调用我们自定义的异常处理类,在异常处理类中对所有的异常进行统一的处理.
SSM整合:
	SpringMVC + Spring + Mybatis
        SpringMVC: 
			负责接收请求
            调用service处理请求
            生成响应
		Spring:
			IOC: 控制反转,进行解耦
            AOP: 面向切面编程,方法增强 --- 管理整个项目的所有事务
        Mybatis:
			对jdbc进行的封装,操作数据库
   	------------------------------
    Spring:
		IOC  ---测试
    Mybatis:
		sqlMapConfig.xml + 接口  ---测试
    Spring整合Mybatis:
		将Mybatis的配置交给Spring管理
        配置Spring的AOP进行事务控制  ---测试
    SpringMVC:
		web.xml + @RequestMapping  ---测试
    SpringMVC和Spring整合:
		在web.xml文件中配置加载Spring的配置文件 ---测试
   --------------------------------
   Spring整合了Mybatis
   web.xml文件:
		1.Spring
            将Spring的配置文件作为参数存放到ServletContext对象中
            	applicationContext.xml
            设置Spring的监听器,通过监听器加载并解析Spring的配置文件
        2.SpringMVC
            2.1: DispatcherServlet 总控制器
                springmvc.xml
            2.2: 配置编码过滤器
    applicationContext.xml文件: Spring配置 +Mybatis
        1.开启包扫描 com.itheima
        	通过扫描到的注解构建IOC容器
       	2.配置声明式事务 AOP体现
        	创建连接池存放到IOC容器
        	配置事务管理平台对象,存放到IOC容器
        	配置事务的相关属性:
				传播行为 是否只读 隔离级别 是否超时
            配置织入: 哪些方法执行时需要做事务增强
        =========================
        3.配置创建SqlSessionFactory对象的bean
            SqlSessionFactoryBean: spring整合Mybatis后提供的类
                创建SqlSessionFactoryBuilder对象,解析主要配置
                使用SqlSessionFactoryBuilder创建SqlSessionFactory对象
                将创建好的SqlSessionFactory存放到IOC容器中
        4.配置sql映射文件的位置,通过sql映射文件找到对应的dao接口,会生成接口的代理类对象
                并将生成的代理类对象存放到IOC容器中
     springmvc.xml:
		1.开启包扫描 com.itheima.controller
            springmvc在包扫描时,扫描到的bean会存放到springmvc的容器中,
			而不是spring的ioc容器
        2.配置视图解析器
        3.对静态资源方行
        4.将可能使用到的组件的实现类加载到IOC容器中
            
```

## Maven高级

## 1.maven基础知识回顾

### 1.1 maven介绍

maven: 专家, 它是项目管理方面的专家

maven 是一个项目管理工具，主要作用是在项目开发阶段对Java项目进行依赖管理和项目构建。

==jar包依赖管理==：就是对jar包的管理。通过导入maven坐标，就相当于将仓库中的jar包导入了当前项目中。

==项目构建==：通过maven的一个命令就可以完成项目从清理、编译、测试、报告、打包，部署整个过程。

![image](https://github.com/wgzdgithub/javaLearn/blob/master/img/%E5%9B%BE%E7%89%871.png)

​              ![](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%871.png)

### 1.2 maven的仓库类型

1.本地仓库 : 安装在自己电脑上的仓库,服务于你自己

2.私服(远程仓库): 公司或组织提供的仓库,服务于当前公司或组织的成员

​		maven私服（公司局域网内的仓库，需要自己搭建）

​		其他公共远程仓库（例如apache提供的远程仓库，地址：http://repo.maven.apache.org/maven2/）

3.中央仓库

​		maven中央仓库（地址：http://repo2.maven.org/maven2/）

### 1.3 maven常用命令

```java
mvn clean： 清理

mvn compile：编译

mvn test： 测试

mvn package：打包

mvn install： 安装
    
mvn tomcat7:run
```



### 1.4 maven坐标书写规范

![1559549224408](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%872.png)



### 1.5 maven的依赖范围

| 依赖范围     | 对于编译classpath有效 | 对于测试classpath有效 | 对于运行时classpath有效 | 例子                        |
| ------------ | --------------------- | --------------------- | ----------------------- | --------------------------- |
| compile      | Y                     | Y                     | Y                       | spring-core                 |
| test         | -                     | Y                     | -                       | Junit                       |
| ==provided== | Y                     | Y                     | -                       | servlet-api                 |
| runtime      | -                     | Y                     | Y                       | JDBC驱动                    |
| system       | Y                     | Y                     | -                       | 本地的，maven仓库之外的类库 |

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <!-- compile:默认值,任何时期都有效 -->
        <!-- <scope>compile</scope>-->
        <!-- test: 此包在test包中有效,其他位置均无效 -->
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.0.1</version>
        <!-- 此jar包已经在其他位置提供了 -->
        <scope>provided</scope>
    </dependency>
</dependencies>
```

## 2. maven的依赖传递

### 2.1 什么是依赖传递

```java
依赖传递:
    当我们在我们的项目中引入一个jar包时
    引入的jar包所依赖的jar也会跟着传递进来
```



在maven中，依赖是可以传递的，假设存在三个项目，分别是项目A，项目B以及项目C。假设C依赖B，B依赖A，那么我们可以根据maven项目依赖的特征不难推出项目C也依赖A。

![1559549336921](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%873.png)



​                         ![1559549377105](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%874.png)

通过上面的图可以看到，我们的web项目直接依赖了spring-webmvc，而spring-webmvc依赖了sping-aop、spring-beans等。最终的结果就是在我们的web项目中间接依赖了spring-aop、spring-beans等。

### 2.2 什么是依赖冲突

```java
在一个项目中同时依赖了多个相同作用的jar
    spring-aop : 5.0.2
    spring-aop : 5.0.5
    
    我们使用maven引入了Servlet的jar包
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
        	<!-- 当前jar包会存放到任意时期 -->
        	<scope>compile</scope>
        </dependency>
    当我们把项目部署到tomcat服务器上时,会报jar包冲突
        在tomcat服务器中已经内置了servlet的jar
    
```



由于依赖传递现象的存在， spring-webmvc 依赖 spirng-beans-4.2.4，spring-aop 依赖 spring-beans-5.0.2，但是发现 spirng-beans-4.2.4 加入到了工程中，而我们希望 spring-beans-5.0.2 加入工程。这就造成了依赖冲突。

![1559549435874](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%875.png)

### 2.3 如何解决依赖冲突

```xml
1.使用maven提供的依赖调解原则 (自动)
	1.1 依赖传递jar包,第一声明者优先原则
    	在pom.xml文件中,先声明哪个jar包,就以那个jar包为主.
	1.2 路径近者优先原则
    	优先使用我们自己导入的jar包
    	依赖中传递的jar包排其次
    	直接依赖高于间接依赖
2.排除依赖
    排除依赖的jar包
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>4.2.4.RELEASE</version>
        <!-- 排除依赖的jar包 -->
        <exclusions>
            <exclusion>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
3.锁定版本 
    <!-- 锁定的jar包版本 -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>5.0.2.RELEASE</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <!-- 导入jar包时,不需要再设置版本 -->
    <dependencies>
    	<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
        </dependency>
    </dependencies>
```

### 2.4 依赖调节原则——第一声明者优先原则

在 pom 文件中定义依赖，以先声明的依赖为准。其实就是根据坐标导入的顺序来确定最终使用哪个传递过来的依赖。

![1559549523188](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%876.png)

结论：通过上图可以看到，spring-aop和spring-webmvc都传递过来了spring-beans，但是因为spring-aop在前面，所以最终使用的spring-beans是由spring-aop传递过来的，而spring-webmvc传递过来的spring-beans则被忽略了。

```java
在pom.xml文件中,先声明哪个jar包,就以那个jar包为主.
```

示例代码:

```xml
<dependencies>
        <!--
            方式1: 依赖调解原则
                1.传递的jar包,第一声明者优先原则
                    在我们的项目中引入了多个jar包
                    而这多个jar包同时传递了功能相同但版本不同的jar包
                    谁先声明,就采用谁传递的jar包
                    注意: 我们自己引入的jar包,后者覆盖前者
                2.路径近者优先原则:
                    直接依赖 > 间接依赖
                    我们直接引入的jar包要优先传递的jar包
        -->
        <!--<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.0.5.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>-->
        <!--<dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>-->

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.0.5.RELEASE</version>
        </dependency>

    </dependencies>
```



### 2.5 排除依赖

可以使用exclusions标签将传递过来的依赖排除出去。

![1559549561284](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%877.png)

示例代码:

```xml
<dependencies>
        <!--
            方式2: 排除依赖
                排除传递的jar包
                <exclusions>
                    <exclusion>
                        <groupId>org.springframework</groupId>
                        <artifactId>spring-beans</artifactId>
                    </exclusion>
                </exclusions>
        -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.0.5.RELEASE</version>
            <!-- 排除传递的jar包 -->
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-beans</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>5.0.2.RELEASE</version>
        </dependency>




    </dependencies>
```



### 2.6 版本锁定

采用直接锁定版本的方法确定依赖jar包的版本，版本锁定后则不考虑依赖的声明顺序或依赖的路径，以锁定的版本为准添加到工程中，此方法在企业开发中经常使用。

版本锁定的使用方式：

第一步：在dependencyManagement标签中锁定依赖的版本

第二步：在dependencies标签中声明需要导入的maven坐标

①在dependencyManagement标签中锁定依赖的版本

![1559549614223](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%878.png)

②在dependencies标签中声明需要导入的maven坐标

![1559549637900](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%879.png)

示例代码:

```xml
    <dependencyManagement>
        <!-- 锁定版本-声明 -->
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-beans</artifactId>
                <version>5.0.2.RELEASE</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!--
            方式3: 锁定版本
        -->
        <!--<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
        </dependency>-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.0.5.RELEASE</version>
        </dependency>
    </dependencies>
```

## 3.基于maven构建SSM工程案例

#### 3.1 创建项目导入jar包坐标

```xml
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <spring.version>5.0.2.RELEASE</spring.version>
        <springmvc.version>5.0.2.RELEASE</springmvc.version>
        <mybatis.version>3.4.5</mybatis.version>
    </properties>
    <!--锁定jar版本-->
    <dependencyManagement>
        <dependencies>
            <!-- Mybatis -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>${mybatis.version}</version>
            </dependency>
            <!-- springMVC -->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>${springmvc.version}</version>
            </dependency>
            <!-- spring -->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aop</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-web</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-expression</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-beans</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context-support</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-tx</artifactId>
                <version>${spring.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <!-- Mybatis和mybatis与spring的整合 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.1</version>
        </dependency>
        <!-- MySql驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.32</version>
        </dependency>
        <!-- druid数据库连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.9</version>
        </dependency>
        <!-- springMVC核心-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <!-- spring相关 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        <!-- aspectJ坐标 切入点表达式-->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.13</version>
        </dependency>
        <!-- Servlet的jar包 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>
        <!-- jsp的jar包 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.0</version>
            <scope>provided</scope>
        </dependency>
        <!-- jstl的jar包-->
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <!-- druid jar包 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.13</version>
        </dependency>
        <!-- c3p0 jar包 -->
        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>
        <!-- junit测试 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

#### 3.2 搭建Spring的环境

applicationContext-service.xml 的配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
    <!-- Spring-IOC: 开启包扫描 -->
    <context:component-scan base-package="com.itheima"></context:component-scan>

    <!-- Spring-AOP: 事务控制 -->
    <!-- 创建事务管理平台 -->
    <!--<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>-->

    <!-- 使用Spring的声明式事务 -->
    <!-- 开启Spring的事务注解支持 -->
    <!--<tx:annotation-driven transaction-manager="transactionManager"/>-->

</beans>
```

#### 3.3 搭建Mybatis的环境

mybatisConfig.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 配置properties文件 -->
    <properties resource="jdbc.properties"></properties>
    <!-- 给包起别名 -->
    <typeAliases>
        <package name="com.itheima.pojo"></package>
    </typeAliases>
    <!-- 数据库连接池的环境 -->
    <environments default="development">
      <environment id="development">
        <transactionManager type="JDBC" />
          <dataSource type="POOLED">
              <property name="driver" value="${jdbc.driver}" ></property>
              <property name="url" value="${jdbc.url}" ></property>
              <property name="username" value="${jdbc.username}"></property>
              <property name="password" value="${jdbc.password}"></property>
          </dataSource>
     </environment>
    </environments>
    <!-- 配置映射文件所在的包:
         要求:
            1.映射文件的名称要和接口的名称保持一致
            2.映射文件所在的路径要和接口的路径保持一致
                映射文件存放在resources目录下
         这种配置既支持映射文件又支持注解
     -->
    <mappers>
        <package name="com.itheima.dao" ></package>
    </mappers>
</configuration>
```

测试类:

```java
package com.itheima.test;

import com.itheima.dao.AccountMapper;
import com.itheima.pojo.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    public static void main(String[] args) throws IOException {
        //1.解析配置文件,得到配置文件中的信息
        // SqlSessionFactoryBuilder: 解析配置文件并生成SQLSessionFactory对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream is = Resources.getResourceAsStream("mybatisConfig.xml");
        //2.解析配置文件,得到SqlSessionFactory对象
        // SqlSessionFactory: 根据解析到的配置信息, 创建SqlSession对象
        SqlSessionFactory sqlSessionFactory = builder.build(is);
        //3.获取SqlSession对象
        // SqlSession: 封装了JDBC操作数据库的具体逻辑
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.获取Dao接口的实现类
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        //5.调用方法执行
        List<Account> list = mapper.findAll();
        for (Account account : list) {
            System.out.println(account);
        }
    }
}

```

#### 3.4 搭建SpringMVC的环境

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!-- 前端控制 -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <!-- 编码过滤器 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

#### 3.5 Spring整合SpringMVC

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!-- 指定配置文件的位置 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext-*.xml</param-value>
    </context-param>
    <!-- 配置监听器 -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <!-- 前端控制 -->
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <!-- 编码过滤器 -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

## 4.分模块构建maven工程

### 4.1 分模块构建maven工程分析

在现实生活中，汽车厂家进行汽车生产时，由于整个生产过程非常复杂和繁琐，工作量非常大，所以车场都会将整个汽车的部件分开生产，最终再将生产好的部件进行组装，形成一台完整的汽车。

![1559550879535](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8712.png)

![1559550904100](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8713.png)

```java
开发模式:
	按照功能模块开发:
		用户模块: 跟用户表相关的功能都属于用户模块
            注册,激活,登录,退出...
            前端 <--> web <--> service <--> dao <--> 数据库
        线路模块
        分类模块
        收藏模块
	按照分层模块开发:  ★★★
		前端:
		web模块:
		service模块: 
		dao模块:
		pojo:
		util:
```

### 4.2 maven工程的继承

在Java语言中，类之间是可以继承的，通过继承，子类就可以引用父类中非private的属性和方法。同样，在maven工程之间也可以继承，子工程继承父工程后，就可以使用在父工程中引入的依赖。继承的目的是为了消除重复代码。

![1559550956068](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8714.png)

### 4.3 maven工程的聚合

在maven工程的pom.xml文件中可以使用<modules>标签将其他maven工程聚合到一起，聚合的目的是为了==进行统一操作==。

例如拆分后的maven工程有多个，如果要进行打包，就需要针对每个工程分别执行打包命令，操作起来非常繁琐。这时就可以使用<modules>标签将这些工程统一聚合到maven工程中，需要打包的时候，只需要在此工程中执行一次打包命令，其下被聚合的工程就都会被打包了。

![1559551000245](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8715.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.itheima</groupId>
    <artifactId>m_parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <!-- 用于聚合其他的maven工程 -->
    <modules>
        <module>../m_son1</module>
        <module>../m_son3</module>
        <module>../maven_ssm105</module>
    </modules>
    <!-- 父工程的打包方式必须为pom -->
    <packaging>pom</packaging>

    <!-- 父工程锁定版本 -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>5.0.2.RELEASE</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```



### 4.4 分模块构建maven工程具体实现

##### ①父工程maven_parent构建

创建父工程,锁定jar包版本,其他子工程继承父工程直接导入jar包即可,不需要再设置jar包的版本了

```xml
    <packaging>pom</packaging>
    <properties>
        <spring.version>5.0.5.RELEASE</spring.version>
        <springmvc.version>5.0.5.RELEASE</springmvc.version>
        <mybatis.version>3.4.5</mybatis.version>
    </properties>
    <!--锁定jar版本-->
    <dependencyManagement>
        <dependencies>
            <!-- Mybatis -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>${mybatis.version}</version>
            </dependency>
            <!-- springMVC -->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webmvc</artifactId>
                <version>${springmvc.version}</version>
            </dependency>
            <!-- spring -->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-core</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aop</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-web</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-expression</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-beans</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-aspects</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context-support</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${spring.version}</version>
            </dependency>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-tx</artifactId>
                <version>${spring.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

##### ②子工程maven_pojo构建

 此工程的作用是,存放pojo对象

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>maven_parent</artifactId>
        <groupId>com.itheima</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>maven_pojo</artifactId>
    <dependencies>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.12</version>
        </dependency>
        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.2</version>
        </dependency>
    </dependencies>
</project>
```

Account实体

```java
public class Account {
    private Integer id;
    private String name;
    private Float money;
    // get set方法...
}
```



##### ③子工程maven_dao构建

   配置maven_dao工程的pom.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>maven_parent</artifactId>
        <groupId>com.itheima</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>maven_dao</artifactId>
    <dependencies>
        <dependency>
            <groupId>com.itheima</groupId>
            <artifactId>maven_pojo</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!-- Mybatis和mybatis与spring的整合 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.1</version>
        </dependency>
        <!-- MySql驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.32</version>
        </dependency>
        <!-- druid数据库连接池 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.9</version>
        </dependency>
        <!-- spring相关 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
        </dependency>
        <!-- junit测试 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
    </dependencies>

</project>
```

​    创建DAO接口和Mapper映射文件

```java
package com.itheima.dao;

import com.itheima.pojo.Account;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface AccountMapper {
    @Select("select * from account ")
    List<Account> findAll();
}
```

​    在resources目录下创建spring配置文件applicationContext-dao.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
    <!-- 配置数据源 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"></property>
        <property name="jdbcUrl" value="${jdbc.url}"></property>
        <property name="user" value="${jdbc.username}"></property>
        <property name="password" value="${jdbc.password}"></property>
    </bean>

    <bean class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"></property>
        <property name="typeAliasesPackage" value="com.itheima.pojo"></property>
    </bean>
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.itheima.dao"></property>
    </bean>
</beans>
```

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/spring105
jdbc.username=root
jdbc.password=root
```

##### ④子工程maven_service构建

​    第一步：创建maven_service工程

​    第二步：配置maven_service工程的pom.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>maven_parent</artifactId>
        <groupId>com.itheima</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>maven_service</artifactId>
    <dependencies>
        
        <dependency>
            <groupId>com.itheima</groupId>
            <artifactId>maven_dao</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

</project>
```

​    第三步：创建Service接口和实现类

```java
package com.itheima.service;

import com.itheima.pojo.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAll();
}

```

```java
package com.itheima.service.impl;

import com.itheima.dao.AccountMapper;
import com.itheima.pojo.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper mapper;
    @Override
    public List<Account> findAll() {
        return mapper.findAll();
    }
}
```

 第四步：创建spring配置文件applicationContext-service.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">

    <context:component-scan base-package="com.itheima">
        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"></context:exclude-filter>
    </context:component-scan>

    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
</beans>
```

##### ⑤子工程maven_web构建

​    第一步：创建maven_web工程，注意打包方式为war

​    第二步：配置maven_web工程的pom.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>maven_parent</artifactId>
        <groupId>com.itheima</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>maven_web</artifactId>
    <packaging>war</packaging>
    <dependencies>
        <dependency>
            <groupId>com.itheima</groupId>
            <artifactId>maven_service</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <!-- Servlet的jar包 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>
        <!-- jsp的jar包 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.0</version>
            <scope>provided</scope>
        </dependency>
        <!-- jstl的jar包-->
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>

</project>
```

​    第三步：创建Controller

```java
package com.itheima.web;

import com.itheima.pojo.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AccountController {
    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Account> list = service.findAll();
        model.addAttribute("list",list);
        return "list";
    }
}

```

​    第四步：创建jsp页面

​    第五步：配置web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath*:applicationContext-*.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```

​    第六步：创建springmvc配置文件springmvc.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:component-scan base-package="com.itheima.web"></context:component-scan>
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/pages/"></property>
        <property name="suffix" value=".jsp"></property>
    </bean>
    <mvc:annotation-driven/>
</beans>
```

### 项目整体结构如下：

1）maven_parent为父工程，其余工程为子工程，都继承父工程maven_parent

2）maven_parent工程将其子工程都进行了聚合 

3）子工程之间存在依赖关系，比如maven_dao依赖， maven_pojo、maven_service依赖maven_dao、 maven_web依赖maven_service

## 5. maven私服

### 5.1 私服说明 

maven仓库分为本地仓库和远程仓库，而远程仓库又分为maven中央仓库、其他远程仓库和私服（私有服务器）。其中，中央仓库是由maven官方提供的，而私服就需要我们自己搭建了。

maven私服就是公司局域网内的maven远程仓库，每个员工的电脑上安装maven软件并且连接maven私服，程序员可以将自己开发的项目打成jar并发布到私服，其它项目组成员就可以从私服下载所依赖的jar。私服还充当一个代理服务器的角色，当私服上没有jar包时会从maven中央仓库自动下载。

nexus 是一个maven仓库管理器（其实就是一个软件），nexus可以充当maven私服，同时nexus还提供强大的仓库管理、构件搜索等功能。

### 5.2 搭建maven私服

①下载nexus

https://help.sonatype.com/repomanager2/download/download-archives---repository-manager-oss

②安装nexus

将下载的压缩包进行解压，进入bin目录

![1559551510928](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8717.png)

打开cmd窗口并进入上面bin目录下，执行`nexus.bat install`命令安装服务（==注意需要以管理员身份运行cmd命令==）

![1559551531544](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8718.png)

③启动nexus

经过前面命令已经完成nexus的安装，可以通过如下两种方式启动nexus服务：

在Windows系统服务中启动nexus

![1559551564441](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8719.png)

在命令行执行`nexus.bat start`命令启动nexus

![1559551591730](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8720.png)

④访问nexus

启动nexus服务后，访问http://localhost:8081/nexus

点击右上角LogIn按钮，进行登录。使用默认用户名`admin`和密码`admin123`登录系统

登录成功后点击左侧菜单Repositories可以看到nexus内置的仓库列表（如下图）

![1559551620133](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%87.png)

![image-20200609162746460](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/image-20200609162746460.png)

nexus仓库类型

通过前面的仓库列表可以看到，nexus默认内置了很多仓库，这些仓库可以划分为4种类型，每种类型的仓库用于存放特定的jar包，具体说明如下：

①hosted，宿主仓库，部署自己的jar到这个类型的仓库，包括Releases和Snapshots两部分，Releases为公司内部发布版本仓库、 Snapshots为公司内部测试版本仓库 

```java
releases: ★发布版,经过测试的代码
snapshots: ★快照版,内部使用的测试版仓库
	我们的项目打好的包,究竟存放到哪个仓库,取决于<version>1.0-SNAPSHOT</version> 
3rd party: 存放第三方的jar包
    Oracle的jar,这个jar包是由Oracle公司提供的,在maven的中央仓库中是不存在的.
    要想使用这类jar包,需要从对应的官网上下载,下载下来以后可以安装到私服或本地仓库.
```

②proxy，代理仓库，用于代理远程的公共仓库，如maven中央仓库，用户连接私服，私服自动去中央仓库下载jar包或者插件

```java
Apache Snapshots: Apache仓库下载的jar包
Central: ★中央仓库下载的jar包
```

③group，仓库组，用来合并多个hosted/proxy仓库，通常我们配置自己的maven连接仓库组

```java
仓库组:
	releases: ★发布版,经过测试的代码
	snapshots: ★快照版,内部使用的测试版仓库
    Central: ★中央仓库下载的jar包
```

④virtual(虚拟)：兼容Maven1版本的jar或者插件

![1559551723693](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8721.png)

nexus仓库类型与安装目录对应关系

![1559551752012](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8722.png)

### 5.3 将项目发布到maven私服

maven私服是搭建在公司局域网内的maven仓库，公司内的所有开发团队都可以使用。例如技术研发团队开发了一个基础组件，就可以将这个基础组件打成jar包发布到私服，其他团队成员就可以从私服下载这个jar包到本地仓库并在项目中使用。

将项目发布到maven私服操作步骤如下：

1. ==配置maven的settings.xml文件==

```xml
在<servers></servers>标签中添加

<server>
    <id>releases</id>
    <username>admin</username>   
    <password>admin123</password>
</server>
<server>
    <id>snapshots</id>
    <username>admin</username>
    <password>admin123</password>
</server>
```

​      注意：一定要在idea工具中引入的maven的settings.xml文件中配置 

1. 配置项目的pom.xml文件

```xml
想将哪个项目打成包发布到私服上,就在那个项目的pom.xml文件中添加相关配置
<distributionManagement>
    <repository>
       <id>releases</id>
       <url>http://localhost:8081/nexus/content/repositories/releases/</url>
    </repository>
    <snapshotRepository>
       <id>snapshots</id>               <url>http://localhost:8081/nexus/content/repositories/snapshots/</url>    </snapshotRepository>
</distributionManagement>
```

1. 执行mvn deploy命令

![1559551977984](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8723.png)

### 5.4 从私服下载jar到本地仓库

前面我们已经完成了将本地项目打成jar包发布到maven私服，下面我们就需要从maven私服下载jar包到本地仓库。

具体操作步骤如下：

在maven的settings.xml文件中配置下载模板

```xml
在<profiles></profiles>标签体中添加如下配置
<profile>
	<id>dev</id>
	<repositories>
		<repository>
			<id>nexus</id>
			<!--仓库地址，即nexus仓库组的地址-->
			<url>http://localhost:8081/nexus/content/groups/public/</url>
			<!--是否下载releases构件-->
			<releases>
			<enabled>true</enabled>
			</releases>
			<!--是否下载snapshots构件-->
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	</repositories>
	<pluginRepositories>
		<!-- 插件仓库，maven的运行依赖插件，也需要从私服下载插件 -->
		<pluginRepository>
			<id>public</id>
			<name>Public Repositories</name>
			<url>http://localhost:8081/nexus/content/groups/public/</url>
		</pluginRepository>
	</pluginRepositories>
</profile>
```

在maven的settings.xml文件中配置激活下载模板

```xml
<activeProfiles>
	<activeProfile>dev</activeProfile>
</activeProfiles>
```

### 小结

```java
安装私服:
	以超级管理员身份 cmd 执行安装命令
    启动: 
	通过浏览器访问nexus服务器:
		http://localhost:8081/nexus
		用户名: admin
        密码: admin123
   	仓库分类:
		releases: ★存放我们发布版代码,经过测试的代码
		snapshots: ★存放我们快照版代码,内部使用的测试版仓库
    	Central: ★中央仓库下载的jar包
	----------------------------
    将我们的项目打包安装到私服中:
		1.在setting.xml文件中配置server
            <server>
                <id>releases</id>
                <username>admin</username>   
                <password>admin123</password>
            </server>
            <server>
                <id>snapshots</id>
                <username>admin</username>
                <password>admin123</password>
            </server>
       2.在需要打包并发布的模块中 配置
            <!-- 配置私服的路径和登录信息 -->
    <distributionManagement>
        <repository>
            <id>releases</id>            		<url>http://localhost:8081/nexus/content/repositories/releases/</url>
        </repository>
        <snapshotRepository>
            <id>snapshots</id>
        <url>http://localhost:8081/nexus/content/repositories/snapshots/</url>
        </snapshotRepository>
    </distributionManagement>
 ================================================
	从私服下载jar包:
		当本地没有jar包时,从我们的私服中下载jar包到本地
        如果私服中也没有jar包,私服会自动找中央仓库
        ----------------
        在setting.xml文件中,配置私服模板(复制),并激活
        

```







## 6. 将第三方jar安装到本地仓库和maven私服(了解)

在maven工程的pom.xml文件中配置某个jar包的坐标后，如果本地的maven仓库不存在这个jar包，maven工具会自动到配置的maven私服下载，如果私服中也不存在，maven私服就会从maven中央仓库进行下载。

但是并不是所有的jar包都可以从中央仓库下载到，比如常用的Oracle数据库驱动的jar包在中央仓库就不存在。此时需要到Oracle的官网下载驱动jar包，然后将此jar包通过maven命令安装到我们本地的maven仓库或者maven私服中，这样在maven项目中就可以使用maven坐标引用到此jar包了。

### 6.1 将第三方jar安装到本地仓库

①下载Oracle的jar包（略）

②mvn install命令进行安装

```java
mvn install:install-file -Dfile=ojdbc14-10.2.0.4.0.jar -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar
```

③查看本地maven仓库，确认安装是否成功

![1559552325997](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/%E5%9B%BE%E7%89%8724.png)

### 6.2 将第三方jar安装到maven私服

①下载Oracle的jar包（略）

②在maven的settings.xml配置文件中配置第三方仓库的server信息

```xml
<server>
  <id>thirdparty</id>
  <username>admin</username>
  <password>admin123</password>
</server>
```

③执行mvn deploy命令进行安装

```java
mvn deploy:deploy-file -Dfile=ojdbc14-10.2.0.4.0.jar -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=10.2.0.4.0 -Dpackaging=jar -Durl=http://localhost:8081/nexus/content/repositories/thirdparty/ -DrepositoryId=thirdparty
```

# 总结

```java
maven:专家,项目管理方面的专家
    用于开发项目的过程中对项目进行管理
    作用:
		jar包依赖管理: maven将所有可能使用到的jar包进行了统一管理.
            开发过程中如果需要使用jar包直接引入jar包坐标即可.
            当我们将项目打包部署时,会将项目依赖的jar包打入到我们的项目包中
      	一键构建项目:
	---------------------
    jar包的依赖传递:
		A项目(我们需要依赖B)
            B.jar(导入B包)
            	C.jar(B包依赖C包)
    	当我们在项目中导入B.jar包时,C.jar包也会被导入. C的加入为间接的依赖--依赖传递
    依赖传递时,可能会有jar包冲突:
		maven的自动调解原则:
			依赖传递的jar包,遵循,第一声明者优先原则.
            路径近者优选原则
                直接依赖 > 间接依赖
        排除指定版本的jar包
        锁定版本
    分模块构建项目:
		将功能相同的java类存放到一个独立的工程(模块),将独立的工程进行打包--jar包
        在需要使用此功能的模块中直接引入jar包即可.
        pom: 父工程,用于被继承的工程
        jar: java工程,当前工程打成jar包
            service --->  dao --->  pojo 
        war: web工程,当前工程打成war包
            Controller层的项目为web项目
        
        继承:
			子工程 继承 父工程,子工程就可以使用父工程指定的规范,或jar包了
		聚合: 
			目的: 为的是进行统一的操作
			在父工程中聚合子工程,操作父工程时也会操作所有聚合的子工程
    ----------------------
安装私服:
	以超级管理员身份 cmd 执行安装命令
    启动: 
	通过浏览器访问nexus服务器:
		http://localhost:8081/nexus
		用户名: admin
        密码: admin123
   	仓库分类:
		releases: ★存放我们发布版代码,经过测试的代码
		snapshots: ★存放我们快照版代码,内部使用的测试版仓库
    	Central: ★中央仓库下载的jar包
	----------------------------
    将我们的项目打包安装到私服中:
		1.在setting.xml文件中配置server
            <server>
                <id>releases</id>
                <username>admin</username>   
                <password>admin123</password>
            </server>
            <server>
                <id>snapshots</id>
                <username>admin</username>
                <password>admin123</password>
            </server>
       2.在需要打包并发布的模块中 配置
            <!-- 配置私服的路径和登录信息 -->
    <distributionManagement>
        <repository>
            <id>releases</id>            		<url>http://localhost:8081/nexus/content/repositories/releases/</url>
        </repository>
        <snapshotRepository>
            <id>snapshots</id>
        <url>http://localhost:8081/nexus/content/repositories/snapshots/</url>
        </snapshotRepository>
    </distributionManagement>
 ================================================
	从私服下载jar包:
		当本地没有jar包时,从我们的私服中下载jar包到本地
        如果私服中也没有jar包,私服会自动找中央仓库
        ----------------
        在setting.xml文件中,配置私服模板(复制),并激活
        
    
	
```

```java
 RPC: 远程服务调用
        web: tomcat
             调用service层的业务
        service: tomcat

```

## 作业

```java
 Spring整合了Mybatis
   web.xml文件:
		1.Spring
            将Spring的配置文件作为参数存放到ServletContext对象中
            	applicationContext.xml
            设置Spring的监听器,通过监听器加载并解析Spring的配置文件
        2.SpringMVC
            2.1: DispatcherServlet 总控制器
                springmvc.xml
            2.2: 配置编码过滤器
    applicationContext.xml文件: Spring配置 +Mybatis
        1.开启包扫描 com.itheima
        	通过扫描到的注解构建IOC容器
       	2.配置声明式事务 AOP体现
        	创建连接池存放到IOC容器
        	配置事务管理平台对象,存放到IOC容器
        	配置事务的相关属性:
				传播行为 是否只读 隔离级别 是否超时
            配置织入: 哪些方法执行时需要做事务增强
        =========================
        3.配置创建SqlSessionFactory对象的bean
            SqlSessionFactoryBean: spring整合Mybatis后提供的类
                创建SqlSessionFactoryBuilder对象,解析主要配置
                使用SqlSessionFactoryBuilder创建SqlSessionFactory对象
                将创建好的SqlSessionFactory存放到IOC容器中
        4.配置sql映射文件的位置,通过sql映射文件找到对应的dao接口,会生成接口的代理类对象
                并将生成的代理类对象存放到IOC容器中
     springmvc.xml:
		1.开启包扫描 com.itheima.controller
            springmvc在包扫描时,扫描到的bean会存放到springmvc的容器中,
			而不是spring的ioc容器
        2.配置视图解析器
        3.对静态资源方行
        4.将可能使用到的组件的实现类加载到IOC容器中
-------------------
SSM环境搭建: maven的锁定版本
分模块构建SSM:
	parent: 父工程 pom
        pojo: jar
        dao: jar
        service: jar
        controller: war
   			web.xml
            	classpath*:application-*.xml
纯注解编程:
	配置类可以复制
    
---------------------扩展
tomcat服务器启动时会加载项目导入的jar包中的...
     在导入的jar包中:
         META-INF
             services
                 javax.servlet.ServletContainerInitializer
                     配置初始化类(初始化类必须实现ServletContainerInitializer)
ServletContainerInitializer: Servlet3.0提供的接口
     给第三方框架提供的入口
@HandlesTypes: 预加载的接口的所有实现类
    此注解写在启动类上,预加载的接口的所有实现类
```

![image-20200912174941818](D:/%E4%B8%8A%E6%B5%B7%E7%BA%BF%E4%B8%8B%E5%B0%B1%E4%B8%9A%E7%8F%AD/Spring/day07-maven%E9%AB%98%E7%BA%A7/resources/img/image-20200912174941818.png)









