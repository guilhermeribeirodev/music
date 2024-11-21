
```shell
curl -X POST -H "Content-Type: application/json" -d '{"title":"Title"}' http://localhost:8080/product
```

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"title":"Title"}' \
  localhost:8080/product
```

```
./gradlew bootRun
```
