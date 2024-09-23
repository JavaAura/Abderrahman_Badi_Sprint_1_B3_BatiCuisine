#!/bin/bash

# Create an output directory if it doesn't exist
SRC_DIR="./src"
CLASS_DIR="./out/production/BatiCuisine"
MANIFEST_FILE="./MANIFEST.MF"
LIB_DIR="./lib"
JAR_DIR="./jar"
JAR_NAME="BatiCuisine.jar"
MAIN_CLASS="Main"
CLASS_PATH="lib/postgresql-42.7.4.jar"
PGSQL_JAR_PATH="./lib/postgresql-42.7.4.jar"

# Compile options
CLASSPATH="$PGSQL_JAR_PATH"
COMPILE_OPTS="-d $CLASS_DIR -classpath $CLASSPATH"

# Create the necessary directories if they don't exist
mkdir -p $CLASS_DIR $JAR_DIR

# Compile all Java files in the project
echo "Compiling java files..."
javac $COMPILE_OPTS $(find $SRC_DIR -name "*.java")


# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful."
else
    echo "Compilation failed."
    exit 1
fi

# Create the manifest file
echo "Creating MANIFEST.MF..."
echo "Manifest-Version: 1.0" > $MANIFEST_FILE
echo "Main-Class: $MAIN_CLASS" >> $MANIFEST_FILE
echo "Class-Path: $CLASS_PATH" >> $MANIFEST_FILE

# Copy lib dir to jar dir
echo "Copying lib folder to jar folder..."
cp -a $LIB_DIR $JAR_DIR

# Package compiled classes into a JAR
echo "Creating JAR file..."
jar cfm $JAR_DIR/$JAR_NAME $MANIFEST_FILE -C $CLASS_DIR .

# Check if JAR creation was successful
if [ $? -eq 0 ]; then
    echo "JAR file created successfully: $JAR_NAME"
else
    echo "Failed to create JAR file."
    exit 1
fi

# Clean up generated files
echo "Cleaning up generated class files..."
rm -rf "$CLASS_DIR"

echo "Cleanup completed."

echo "Running project"
java -jar $JAR_DIR/$JAR_NAME

