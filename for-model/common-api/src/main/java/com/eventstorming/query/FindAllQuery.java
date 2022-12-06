forEach: View
representativeFor: View
fileName: {{namePascalCase}}Query.java
path: common-api/{{{options.packagePath}}}/query
---
package {{options.package}}.query;

import org.springframework.format.annotation.DateTimeFormat;
{{importTypes queryParameters}}

public class {{namePascalCase}}Query {

    {{#queryParameters}}
    {{#ifEquals className "Date"}}
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    {{/ifEquals}}
    {{{className}}} {{nameCamelCase}}
    {{/queryParameters}}
    
}


<function>

window.$HandleBars.registerHelper('importTypes', function (fieldDescriptors) {
    var imports = "";

    var typeMappings = {
        "Date": "java.util.Date",
        "BigDecimal": "java.math.BigDecimal"
    };

    for(var i = 0; i < fieldDescriptors.length; i ++ ){
        if(fieldDescriptors[i]){
            var fullTypeName = typeMappings[fieldDescriptors[i].className];

            if(fullTypeName){
                imports += "import " + fullTypeName + "\n";
                typeMappings[fieldDescriptors[i].className] = null;
            }
        } 
    }

    return imports;
});


window.$HandleBars.registerHelper('ifEquals', function(arg1, arg2, options) {
    return (arg1 == arg2) ? options.fn(this) : options.inverse(this);
});

</function>