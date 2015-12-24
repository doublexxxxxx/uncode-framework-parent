1、警告：The tag handler class for "s:form" (org.apache.struts2.views.jsp.ui.FormTag) was not found on the Java Build Path
这个问题终于可以解决了， 在出问题的页面  <s:form> 标签前后删除空格后保存文件 警告即可消失，即<s:form>为改行的起始，然后保存。

 

2、Eclipse下Java compiler level does not match the version of the installed Java project facet的问题 .

javacompilerfaceteclipsepropertiesjdk问题现象：项目图标报错“Java compiler level does not match the version of the installed Java project facet.”。
分析原因：根据错误信息的字面意思，应该是Java版本设定不一致的问题。而且这个Java版本跟Project安装的有关系。

解决办法：

既然是project出的问题，自然要从项目属性入手。其实很简单，

修改项目的org.eclipse.wst.common.project.facet.core.xml
 <installed facet="jst.web" version="2.5"/>  --Dynamic Web Moudle Version
 <installed facet="java" version="1.5"/>   --java Version
 根据自身机器开发环境设置
 
 或者
  右键项目“Properties”，在弹出的“Properties”窗口左侧，单击“Project Facets”，打开“Project Facets”页面。

在页面中的“Java”下拉列表中，选择“1.5”

 

3、Struts2 + Spring
需要在pom.xml中加入：
 <!-- struts2 spring plugins -->
  <dependency>
   <groupId>org.apache.struts</groupId>
   <artifactId>struts2-spring-plugin</artifactId>
   <version>2.3.16</version>
  </dependency>
  
同时在struts.xml中加入
 <!-- 将Action交给spring容器管理 -->
 <constant name="struts.objectFactory" value="spring" />
 
 
 
 
 4、Eclipse : An error occurred while filtering resources
 解决：Maven -> Update Project... resolved this problem for me


 5、org.dom4j.DocumentException:Software caused connection abort: recv failed

这个错误有可能是hibernate的配置文件.hbm.xml里

<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

这个声明有问题

用下面这个解决问题

<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

如果不行，则是颠倒过来，即如果原来使用

<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
则用

<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

 

6、Cannot change version of project facet Dynamic Web Module to 3.0. 和 One or more constraints have not been satisfied.

需要修改 project的 properties的 project facts的 Dynamic Web Moudle Version版本和 web.xml中的版本一直，然后 在通过maven update

以下为 web.xml的各个版本：

web.xml v2.3
[xml] view plaincopy在CODE上查看代码片派生到我的代码片

    <?xml version="1.0" encoding="ISO-8859-1"?>  
    <!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN" "http://java.sun.com/dtd/web-app_2_3.dtd">  
       
    <web-app>  
       
    </web-app>  

 

web.xml v2.4

 
[xml] view plaincopy在CODE上查看代码片派生到我的代码片

    <?xml version="1.0" encoding="UTF-8"?>  
    <web-app id="WebApp_9" version="2.4" xmlns="http://java.sun.com/xml/ns/j2ee"   
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
        xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">  
       
    </web-app>  

 

web.xml v2.5

 
[xml] view plaincopy在CODE上查看代码片派生到我的代码片

    <?xml version="1.0" encoding="UTF-8"?>   
    <web-app xmlns=<a target="_blank" href="http://java.sun.com/xml/ns/javaee">http://java.sun.com/xml/ns/javaee</a>   
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"   
    version="2.5">  
       
    </web-app>  

 

web.xml v3.0

 
[xml] view plaincopy在CODE上查看代码片派生到我的代码片

    <?xml version="1.0" encoding="UTF-8"?>  
       
    <web-app  
            version="3.0"  
            xmlns="http://java.sun.com/xml/ns/javaee"  
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
            xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">  
       
    </web-app>  

 

 

7、An internal error occurred during:"Updating Maven Project"
（

mvn archetype:generate -DgroupId=com.mkyong -DartifactId=HibernateExample 
-DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

） --java  application

 

（

mvn archetype:generate -DgroupId=com.mkyong -DartifactId=HibernateExample 
-DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

） --java  web application

 

命令行创建 maven 项目转为 eclipse项目时， 在eclipse中打开存在的maven项目，不能通过 maven update解决：

问题提示：An internal error occurred during:"Updating Maven Project"
解决：
1、把该项目从eclipse中删除，不要删除掉项目文件(只是从eclipse项目视图中删除)；
2、进入cmd ，然后进入到该项目目录下比如：E:\workspace\mavenProject ,其中mavenProject为项目名称；
3、执行命令：mvn eclipse:clean;
4、检查项目目中是否还存在 .classpath、.project、.settings ,如果存在 ，则删除掉；
5、通过右键-->import--> Existing Maven Projects,重新导入即可。

 

8、

hibernate    java.lang.UnsupportedOperationException: The user must supply a JDBC connection

是 hibernate的 配置文件hibernate.cfg.xml配置存在问题 ，检查数据连接的driver_class、url、username、root等参数名及参数值是否正确

 

 9、mysql 中，如果hibernate的 主键 用 uuid.hex，报异常如下：

java.sql.BatchUpdateException: Data truncation: Data too long for column 'SID' at row 1 .

解决：
  修改mysql的字符集和创建表的一样即可 ，比如创建表 create table aa() default charset=utf8 ;
 
  那么通过命令：set  names utf8 ; ，修改 mysql的字符集；

重新运行程序即可。

 

 

10、maven 在 eclipse中创建maven project报一下错误解决：

Could not resolve archetypeorg.apache.maven.archetypes:maven-archetype-quickstart:RELEASE from any of theconfigured repositories.

Could not resolve artifact

Failed to resolve version fororg.apache.maven.archetypes:maven-archetype-quickstart:pom:RELEASE: Could notfind metadataorg.apache.maven.archetypes:maven-archetype-quickstart/maven-metadata.xml inlocal (E:\maven3\repository)

Failed to resolve version fororg.apache.maven.archetypes:maven-archetype-quickstart:pom:RELEASE: Could notfind metadataorg.apache.maven.archetypes:maven-archetype-quickstart/maven-metadata.xml inlocal (E:\maven3\repository)

 

解决思路：

a、修改conf/settings.xml,在<mirrors>…</mirror>中间加入以下内容：

<mirror>

      <id>ibiblio.org</id>

      <name>ibiblio Mirror ofhttp://repo1.maven.org/maven2/</name>

     <url>http://mirrors.ibiblio.org/pub/mirrors/maven2</url>

      <mirrorOf>central</mirrorOf>

      <!-- United States, North Carolina-->

    </mirror>

   

   <mirror>

      <id>cica.es</id>

      <url>http://ftp.cica.es/mirrors/maven2</url>

      <mirrorOf>central</mirrorOf>

      <!-- Spain, Sevilla -->

    </mirror>

  

    <mirror>

      <id>skynet.be</id>

     <url>http://maven2.mirrors.skynet.be/pub/maven2</url>

      <mirrorOf>central</mirrorOf>

     <!-- Belgium -->

    </mirror>

b、如果机器使用代理，则需要修改代理信息，在<proxies>…</proxies>之间加入

   <proxy>

            <id>my-proxy</id>  <!—起一个名字-->

            <active>true</active> <!—固定为true-->

            <protocol>http</protocol><!—固定为http-->

      <username>user</username><!—如果不需要设置，则屏蔽掉username属性—>

     <password>pwd</password><!—如果不需要设置，则屏蔽掉password属性—>

            <host>10.17.75.2</host><!—代理服务器地址-->

       <port>3128</port><!—代理服务器端口-->

           <nonProxyHosts>localhost|127.0.0.1</nonProxyHosts>

 </proxy>

 

 11、eclipse 执行 mvn install的时候 报如下错误
[ERROR] Failed to parse plugin descriptor for org.apache.maven.plugins:maven-install-plugin:2.3.1 (E:\maven3\repository\org\apache\maven\plugins\maven-install-plugin\2.3.1\maven-install-plugin-2.3.1.jar): error in opening zip file -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginDescriptorParsingException

解决方法：

删除 ：E:\maven3\repository\org\apache\maven\plugins\maven-install-plugin\2.3.1 目录 ，操作后 ：E:\maven3\repository\org\apache\maven\plugins\maven-install-plugin\下没有2.3.1目录，然后重新执行 mvn Install ；

如果 执行 mvn Clear存在相同问题 ，这只需要删除对应的目录，让Maven重新下载对应的jar即可。