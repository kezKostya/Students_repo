rem @echo off
rem Path to JDK installation folder
rem set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_51
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_60



rem set JAVA_OPTS=-Xms1024m -Xmx4000m -XX:MaxPermSize=2048m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8011 -Dorg.apache.el.parser.SKIP_IDENTIFIER_CHECK=true
rem set MAVEN_OPTS=-Xms1024m -Xmx5000m -XX:MaxPermSize=1024m


set "WORK_ROOT=%cd%"
set CONF_DIR=%WORK_ROOT%\config
set "CATALINA_HOME_APP=%WORK_ROOT%\tomcat"
cd ..
set "PARENT_DIR=%cd%"
set STUDENTS_APP_HOME=%PARENT_DIR%
cd %WORK_ROOT%
