#!/usr/bin/env sh

curl -X 'POST' \
  'http://localhost:9020/schemas?create_transaction_for_endorser=false' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "attributes": [
    "firstName",
"lastName",
"birthDate",
"validityStart",
"validityEnd",
"licenceNumber",
"category"
  ],
  "schema_name": "drivingLicense",
  "schema_version": "2.0"
}'
