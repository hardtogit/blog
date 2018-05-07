@echo off
echo [INFO] Re-create the schema and provision the sample data.

cd ../building-web
call mvn antrun:run -Prefresh-db

pause