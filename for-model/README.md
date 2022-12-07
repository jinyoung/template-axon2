forEach: Model
fileName: README.md
path: for-model
---
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

<function>

window.$HandleBars.registerHelper('ifEquals', function (jsonPath, value, options) {
    return (arg1 == arg2) ? options.fn(this) : options.inverse(this);
});

window.$HandleBars.registerHelper('ifContains', function (jsonPath, value, options) {
    var evaluatedVal = window.jp.query(this, jsonPath);
    if( evaluatedVal == value || evaluatedVal.includes(value)
        //(Array.isArray(evaluatedVal) && evaluatedVal.includes(value))
        //|| (typeof evaluatedVal === 'string' && evaluatedVal.con)    -->
    ){
        return options.fn(this)
    }else{
        return options.inverse(this)
    }

});

</function>
