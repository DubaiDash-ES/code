MODULES=(
	dubaidash
	dubaidashApi
)

for i in ${!MODULES[@]}; do

	cd ${!MODULES[$i]}
	rm -rf target
	mvn test

	if [[ "$?" -ne 0 ]] ; then
		rm -rf target

		mvn test

		if [[ "$?" -ne 0 ]] ; then
			echo "Failed tests on ${MODULES[$i]}" 1>&2
			exit 1
		fi
	fi
done
