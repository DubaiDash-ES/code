#!/bin/bash
MODULES=(
	dubaidash
	dubaidashApi
)

for i in ${!MODULES[@]}; do
	cd ${MODULES[$i]}

	rm -rf target
	mvn -X -Dmaven.test.skip=true deploy
	cd ..
done
