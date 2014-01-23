#!/bin/sh

if [ $# -lt 1 ]
then
	echo "Usage: $0 LOCATIONS_FILE.in [MUTATION_PROBABILITY] [CROSSOVER_PROBABILITY] [greedystart]"
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

mvn -q exec:java -Dexec.mainClass=testing.Osm -Dexec.args="$pm $pc $4" < $1 > $ansfile
echo $ansfile `date`
