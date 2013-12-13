#!/bin/sh

if [ $# -lt 1 ]
then
	echo "Usage: $0 TEST_FILE.in [MUTATION_PROBABILITY] [CROSSOVER_PROBABILITY]"
	return
fi

ansfile="${1%.*}.ans"

java -cp bin testing/Main $2 $3 < $1 > $ansfile
echo $ansfile
