@echo off
call setvars.cmd

rem Setting up variables if distinct Tomcat instance defined
if defined CATALINA_HOME_APP set CATALINA_HOME=%CATALINA_HOME_APP%
if defined JAVA_OPTS_APP set JAVA_OPTS=%JAVA_OPTS_APP%

echo Starting Tomcat...
cd %CATALINA_HOME_APP%\bin
call startup.bat
cd %WORK_ROOT%
