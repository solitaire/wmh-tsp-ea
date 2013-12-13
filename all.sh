#!/bin/sh

for pm in 0.1 0.5 0.9 1
do
	for pc in 0.1 0.5 0.9 1
	do
		for file in tests/*in
		do
			echo $file $pm $pc `date`
			./run.sh $file $pm $pc
		done
	done
done
