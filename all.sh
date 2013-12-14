#!/bin/sh

run() {
	for file in tests/*in
	do
		./run.sh $file $1 $2
	done
}

for pm in 0.1 0.5 0.9 1.0
do
	run $pm 0.0
done

for pc in 0.1 0.5 0.9 1.0
do
	run 0.0 $pc
done
