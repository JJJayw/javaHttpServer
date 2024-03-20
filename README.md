# javaHttpServer

基于Java实现webServer

## 项目结构


* controller 控制器
  * IndexServlet
  * LoginServlet
  * RegisterServlet
  * UserServlet
* core Http核心类
  * Configuration 
  * Container
  * Filter
  * HttpRequest
  * HttpRequestHandler
  * HttpResponse
  * HttpResponseHandler
  * Servlet
  * ServletContainer
  * Session
* dao 数据库操作类
  * UserDao User的数据库操作类
* entity 数据库实例类
  * User User实例
* util 工具类
  * IOUtils IO流工具类
  * JDBCUtil JDBC工具类
* WebServer.java java服务器入口

## 配置项目

使用XML文件进行配置，在Configuration类中加载XML文件。

### JDBC配置

```xml

<web-app>
    <data-source>
        <properties name="url" value="URL"/>
        <properties name="username" value="username"/>
        <properties name="password" value="password"/>
        <properties name="driverName" value="Driver"/>
    </data-source>
</web-app>
```

### Servlet配置

```xml

<web-app>
    <servlets>
        <servlet>
            <url>/xxx.do</url>
            <!--            servlet-class 使用类全限定名-->
            <servlet-class>xxx.xxx.xxxServlet</servlet-class>
        </servlet>
    </servlets>
</web-app>
```

### 端口配置

```xml

<web-app>
    <port>8888</port>
</web-app>
```

### www目录配置

```xml

<web-app>
    <base-url>D:/www</base-url>
</web-app>
```
