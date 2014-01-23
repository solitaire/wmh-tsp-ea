#!/bin/sh

if [ ! -e "bin" ]
then
	echo "Missing 'bin' directory. Run './build.sh'."
	exit 1
fi

if [ $# -lt 1 ]
then
	echo "Usage: $0 TEST_FILE.in [MUTATION_PROBABILITY] [CROSSOVER_PROBABILITY] [N_TO_D_RATIO] [greedystart]"
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

ratio=10
if [ $# -gt 3 ]
then
	ratio=$4
fi

g=""
if [ $# -gt 4 ]
then
	g="greedystart"
fi

ansfile="${1%.*}_$ratio.ans"

java -cp bin testing/Main $pm $pc $ratio $g < $1 > $ansfile
echo $ansfile `date`
