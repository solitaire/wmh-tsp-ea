#!/bin/sh
 
java -Dgraphhopper.config=config-example.properties -Dgraphhopper.osmreader.osm=europe_poland.pbf -jar jetty.jar --port 8989 graphhopper-web.war
