@echo off
echo [Pre-Requirement] Makesure install JDK 6.0+ and set the JAVA_HOME.
echo [Pre-Requirement] Makesure install Maven 3.0.3+ and set the PATH.

set MVN=mvn
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m

echo [Step 1] Install all archetype to local maven repository.
cd ..
call %MVN% clean install -Dmaven.test.skip=true
if errorlevel 1 goto error

echo [Step 2] Initialize schema and data for projects.
cd material-web
call %MVN% antrun:run -Prefresh-db
if errorlevel 1 goto error
cd ..

echo [Step 3] Start projects.
cd material-web
call %MVN% jetty:run -Djetty.port=80
if errorlevel 1 goto error

echo [INFO] Please wait a moment. When you see "[INFO] Started Jetty Server" in consoles, you can access follow site:
echo [INFO] http://localhost/material

goto end
:error
echo Error Happen!!
:end
pause