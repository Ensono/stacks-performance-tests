#!/bin/bash

baseUri=$1


if [ -z "$baseUri" ]
then
  echo "please specify base url e.g. tearDownDeleteMenuItems.sh http://localhost:9000"
  exit
fi

menuIds=()
url="${baseUri}/v1/menu?searchTerm=PERF%20TEST&pageSize=20&pageNumber=1"
acceptHeader="accept: application/json"

count=$(curl -X GET "${url}" -H  "${acceptHeader}" | jq -r '.results | length')


while [ $count -gt 0 ]
do
  response=$(curl -X GET "${url}" -H  "${acceptHeader}" | jq -r '. | .results[].id')
  menuIds+=( $response )

  for id in "${menuIds[@]}"; do
    echo $id
  done

  if [ ${#menuIds[@]} -gt 0 ]
  then
    echo "There are ${#menuIds[@]} menu items to be deleted...."

    for id in "${menuIds[@]}"; do
      deleteResponse=$(curl -X DELETE "${baseUri}/v1/menu/${id}" -H  "${acceptHeader}")
      echo "${deleteResponse}"
    done
  fi

  menuIds=()
  count=$(curl -X GET "${url}" -H  "${acceptHeader}" | jq -r '.results | length')
  echo "Number of menu items to delete from latest request : ${count}"
done
