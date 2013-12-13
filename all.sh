#!/bin/sh

run() {
	for file in tests/*in
	do
		echo $file $1 $2 `date`
		./run.sh $file $1 $2
	done
}

for pm in 0.1 0.5 0.9 1
do
	run $pm 0.9
done

for pc in 0.1 0.5 0.9 1
do
	run 0.1 $pc
done
