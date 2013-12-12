#!/bin/sh

for file in tests/*in
do
	./run.sh $file
done
