#!/bin/bash
echo "[INFO] Packaging war."

cd ..

set MAVEN_OPTS=%MAVEN_OPTS%
mvn clean install

echo "[INFO] packaged as xxx.war"