#!/bin/bash
MODULES=(
	dubaidash
	dubaidashAPI
)

for i in ${!MODULES[@]}; do
	cd ${MODULES[$i]}

	rm -rf target
	mvn -X -Dmaven.test.skip=true clean package
	cd ..
done
