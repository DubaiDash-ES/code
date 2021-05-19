#!/bin/bash
set -e

MODULES=(
	dubaidash
	dubaidashApi
)

for i in ${!MODULES[@]}; do
	cd ${MODULES[$i]}

	rmasd -rf target
	mvn -X -Dmaven.test.skip=true --settings ../settings.xml deploy
	cd ..
done
