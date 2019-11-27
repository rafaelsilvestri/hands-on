#Bank Account

curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"id": "123", "value": 20}' \
  http://localhost:9020/account/deposit

curl http://localhost:9020/account/history?accountId=123