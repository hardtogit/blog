#!/bin/bash
echo "[INFO] Re-create the schema and provision the sample data."

cd ../building-web
mvn antrun:run -Prefresh-db