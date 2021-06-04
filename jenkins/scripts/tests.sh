#!/bin/bash
set -e
MODULES=(
	dubaidash
	dubaidashApi
)

for i in ${!MODULES[@]}; do

	cd ${MODULES[$i]}
	rm -rf target
	ls
	mvn test -Drun.arguments='--spring.datasource.url=jdbc:mysql://192.168.160.87:20006/dubaidash?useSSL=false,spring.kafka.consumer.bootstrap-servers=192.168.160.87:20002,spring.kafka.producer.bootstrap-servers=10.10.1.3:20002'

	if [[ "$?" -ne 0 ]] ; then
		rm -rf target

		mvn test

		if [[ "$?" -ne 0 ]] ; then
			echo "Failed tests on ${MODULES[$i]}" 1>&2
			exit 1
		fi
	fi
	cd ..
done
