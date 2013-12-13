#!/bin/sh

for file in tests/*in
do
	./run.sh $file $1 $2
done
