


forEach: View
representativeFor: View
fileName: {{namePascalCase}}SingleQuery.java
path: {{boundedContext.name}}/{{{options.packagePath}}}/query
---
package {{options.package}}.query;

import lombok.Data;

@Data
public class {{namePascalCase}}SingleQuery {

{{#fieldDescriptors}}
    {{#isKey}}
        private {{className}} {{nameCamelCase}};
    {{/isKey}}
{{/fieldDescriptors}}

}
