#!/bin/sh

if [ $# -ne 1 ]
then
	echo "Usage: $0 tests/0.in"
fi

ansfile="${1%.*}.ans"

java -cp bin testing/Main < $1 > $ansfile
echo $ansfile
