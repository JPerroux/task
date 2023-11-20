#!/bin/bash

# Script to wait for MongoDB to be ready before starting the application

set -e

host="localhost"
port="27017"
timeout=30
cmd="nc -zv $host $port"

echo "Waiting for MongoDB to be ready..."

# Wait for MongoDB to be reachable
until $cmd >/dev/null 2>&1 || [ $timeout -eq 0 ]; do
  sleep 1
  timeout=$((timeout-1))
done

if [ $timeout -eq 0 ]; then
  >&2 echo "Error: MongoDB not reachable within the specified timeout."
  exit 1
fi

echo "MongoDB is ready. Starting the application."

# Execute the command to start the application
exec "$@"
