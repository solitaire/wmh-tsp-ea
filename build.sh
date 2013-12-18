#!/bin/sh

if [ ! -e "bin" ]
then
	mkdir bin
fi

javac -d bin $(find src/* | grep "\.java")
