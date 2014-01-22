#!/bin/sh

if [ $# -lt 1 ]
then
	echo "Usage: $0 CITIES"
	return
fi

for file in tests/random/$1/*in
do
	time -f "%Us" ./run.sh $file
done
