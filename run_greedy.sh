#!/bin/sh

if [ $# -lt 1 ]
then
	echo "Usage: $0 TEST_FILE.in"
	return
fi

ansfile="${1%.*}.greedy"

java -cp bin testing/Main greedy < $1 > $ansfile

echo $ansfile `date`
