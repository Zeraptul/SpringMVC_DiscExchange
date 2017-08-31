echo on

rem build project
call mvn clean package -Dmaven.test.skip=true 

rem stop tomcat server
call "%CATALINA_HOME%\bin\shutdown.bat"

rem copy war to tomcat deploy directory
copy "target\SpringMVC_DiscExchange.war" "%CATALINA_HOME%\webapps\my.war"

rem Run tomcat server
call "%CATALINA_HOME%\bin\startup.bat"
rem call "%CATALINA_HOME%\bin\catalina.bat start"

rem "%CATALINA_HOME%\bin\shutdown.bat"
rem call "%CATALINA_HOME%\bin\catalina.bat stop"

rem - start default browser
start http://localhost:8080/my

pause
