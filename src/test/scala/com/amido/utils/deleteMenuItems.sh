#!/bin/bash

menuIds=()

response=$(curl -X GET "http://localhost:9000/v1/menu?searchTerm=PERF%20TEST&pageSize=20&pageNumber=1" -H  "accept: application/json" | jq -r '. | .results[].id')
menuIds+=( $response )

# shellcheck disable=SC2068
for id in ${menuIds[@]}; do
  echo $id
done

if [ ${#menuIds[@]} -gt 0 ]
then
  echo "There are ${#menuIds[@]} menu items to be deleted...."

  # shellcheck disable=SC2068
  for id in ${menuIds[@]}; do
    deleteResponse=$(curl -i -X DELETE "http://localhost:9000/v1/menu/${id}" -H  "accept: application/json")
    echo "${deleteResponse}"
  done
fi


