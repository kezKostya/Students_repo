@echo off
call setvars.cmd


rem Setting up variables if distinct Tomcat instance defined




set TARGET_FOLDER=%CATALINA_HOME_APP%\conf\Catalina\localhost
set TARGET_FILE=students-app.xml
echo.
echo Copying %CONF_DIR%\%TARGET_FILE% to Tomcat (%TARGET_FOLDER%\%TARGET_FILE%)
copy %CONF_DIR%\%TARGET_FILE% %TARGET_FOLDER%\%TARGET_FILE%
fart -c %TARGET_FOLDER%\%TARGET_FILE% "STUDENTS_APP_HOME" "%STUDENTS_APP_HOME%"
fart -c %TARGET_FOLDER%\%TARGET_FILE% "DB_URL" "%DB_URL%"
fart -c %TARGET_FOLDER%\%TARGET_FILE% "DRYIVER_CLASS_NAME" "%DRYIVER_CLASS_NAME%"
fart -c %TARGET_FOLDER%\%TARGET_FILE% "DB_USER" "%DB_USER%"
fart -c %TARGET_FOLDER%\%TARGET_FILE% "DB_PASS" "%DB_PASS%"


