#!/bin/sh

if [ $# -lt 3 ]
then
	echo "Usage: $0 CITIES_FILE.in NUMBER_OF_CITIES NUMBER_OF_ROUTES"
	return
fi

input=$1
cities=$2
routes=$3

for r in $(seq 1 $routes)
do
	shuf -n $cities $input > tests/$cities/$r.loc
	mvn -q exec:java -Dexec.mainClass=graph.builder.GraphBuilder < tests/$cities/$r.loc > tests/$cities/$r.in
done