#!/bin/sh

if [ $# -lt 1 ]
then
	echo "Usage: $0 TEST_FILE.in"
	return
fi

ansfile="${1%.*}.greedy"

mvn -q exec:java -Dexec.mainClass=testing.Osm -Dexec.args="greedy" < $1 > $ansfile
echo $ansfile `date`
