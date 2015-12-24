在web.xml中加入
<!--初始化日志配置文件 -->  
 <listener>  
     <listener-class>  
         org.uncodeframework.core.common.logback.LogbackConfigListener
     </listener-class>  
 </listener>  
 <context-param>  
     <param-name>logbackConfigLocation</param-name>  
     <param-value>WEB-INF/logback.xml</param-value>  
 </context-param> 