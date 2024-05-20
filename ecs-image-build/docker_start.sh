#!/bin/bash
#
# Start script for chd-order-api

PORT=8080
exec java -jar -Dserver.port="${PORT}" "chd-order-api.jar"
