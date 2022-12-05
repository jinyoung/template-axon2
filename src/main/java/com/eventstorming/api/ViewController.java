
forEach: View
fileName: {{namePascalCase}}QueryController.java
path: {{boundedContext.name}}/{{{options.packagePath}}}/api
_except: {{contexts.isNotCQRS}}

---
package {{options.package}}.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import {{options.package}}.query.*;


  {{#contexts.target}}
  
@RestController
public class {{namePascalCase}}QueryController {

  private final QueryGateway queryGateway;


  public {{namePascalCase}}QueryController(QueryGateway queryGateway) {
      this.queryGateway = queryGateway;
  }
  

  @GetMapping("/{{namePlural}}")
  public CompletableFuture findAll() {
      return queryGateway.query(new {{namePascalCase}}Query(), ResponseTypes.multipleInstancesOf({{namePascalCase}}.class))
              .thenApply(resources -> {
                CollectionModel<{{namePascalCase}}> model = CollectionModel.of(resources);
                
                return new ResponseEntity<>(model, HttpStatus.OK);
            });

  }

  @GetMapping("/{{namePlural}}/{id}")
  public CompletableFuture findById(@PathVariable("id") {{../contexts.keyFieldClass}} id) {
    {{namePascalCase}}SingleQuery query = new {{namePascalCase}}SingleQuery();
    query.set{{../contexts.keyField}}(id);

      return queryGateway.query(query, ResponseTypes.optionalInstanceOf({{namePascalCase}}.class))
              .thenApply(resource -> {
                if(!resource.isPresent()){
                  return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }

                EntityModel<{{namePascalCase}}> model = EntityModel.of(resource.get());
                model
                      .add(Link.of("/{{namePlural}}/" + resource.get().get{{contexts.keyField}}()).withSelfRel());
              
                return new ResponseEntity<>(model, HttpStatus.OK);
            }).exceptionally(ex ->{
              throw new RuntimeException(ex);
            });

  }

  {{/contexts.target}}

}

<function>
this.contexts.isNotCQRS = this.dataProjection!="cqrs"

this.contexts.keyField = "Long";
this.contexts.keyFieldClass = "String";
var me = this;

if(this.dataProjection == "query-for-aggregate"){
  this.contexts.target = this.boundedContext.aggregates[0];

  this.contexts.target.aggregateRoot.fieldDescriptors.forEach(fd => {if(fd.isKey) {
      me.contexts.keyField=fd.namePascalCase;
      me.contexts.keyFieldClass=fd.className;
    }
  });

// alert(this.contexts.target.namePascalCase)
}else{
  this.contexts.target = this;

  this.contexts.target.fieldDescriptors.forEach(fd => {if(fd.isKey) {
    me.contexts.keyField=fd.namePascalCase;
    me.contexts.keyFieldClass=fd.className;
  }
});

}


</function>