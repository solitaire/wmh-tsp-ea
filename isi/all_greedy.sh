#!/bin/sh

for file in tests/*in
do
	./run_greedy.sh $file 
done
