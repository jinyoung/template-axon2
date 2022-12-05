## How to run

- Run axon server and mysql firstly

```
cd infra
docker-compose up
```

## Build common API & Run each service

'''
cd common-api
mvn install
cd ..

{{#boundedContexts}}
cd {{name}}
mvn spring-boot:run
cd ..

{{/boundedContexts}}
'''
