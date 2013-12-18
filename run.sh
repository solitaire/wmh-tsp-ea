#!/bin/sh

if [ ! -e "bin" ]
then
	echo "Missing 'bin' directory. Run './build.sh'."
	exit 1
fi

if [ $# -lt 1 ]
then
	echo "Usage: $0 TEST_FILE.in [MUTATION_PROBABILITY] [CROSSOVER_PROBABILITY] [greedystart]"
	return
fi

pm=0.1
if [ $# -gt 1 ]
then
	pm=$2
fi

pc=0.9
if [ $# -gt 2 ]
then
	pc=$3
fi

g=""
if [ $# -gt 3 ]
then
	g="g"
fi

ansfile="${1%.*}_$pm"_"$pc$g.ans"

java -cp bin testing/Main $pm $pc $4 < $1 > $ansfile
echo $ansfile `date`
