@echo off
echo [INFO] Packaging war.

cd ..

set MAVEN_OPTS=%MAVEN_OPTS%
call mvn clean install

echo [INFO] packaged as xxx.war

pause