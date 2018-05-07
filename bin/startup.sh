#!/bin/bash

echo "[Pre-Requirement] Makesure install JDK 6.0+ and set the JAVA_HOME."
echo "[Pre-Requirement] Makesure install Maven 3.0.3+ and set the PATH."
	
set MAVEN_OPTS=$MAVEN_OPTS -XX:MaxPermSize=128m

echo "[Step 1] Install all modules and archetype to local maven repository."
cd ..
mvn clean install -Dmaven.test.skip=true

echo "[Step 2] Initialize schema and data for project."
cd material-web
#mvn antrun:run -Prefresh-db
cd ..

echo "[Step 3] Start projects."
cd material-web

rm -rf *.log.*
mvn clean jetty:run -Djetty.port=80

echo "[INFO] Please wait a moment then access follow site:"
echo "[INFO] http://localhost/material"