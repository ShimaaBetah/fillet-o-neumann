#!/usr/bin/bash

# if the arg is build then build the project

# if the arg is run then run the project
if [ "$1" == "run" ]; then
    echo "Running the project"
    mvn compile exec:java -Dexec.mainClass=fillet.App
fi

if [ "$1" == "test" ]; then
    echo "Testing the project"
    mvn test
fi
