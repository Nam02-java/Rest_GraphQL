package thrid_step.resource_graphQL;

import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thrid_step.Service_graphQL.datafetcher.GraphQLService;

@RequestMapping("/graphql")
@RestController
public class PersonResource {

    @Autowired
    GraphQLService graphQLService;
    public static String requestBody;

    @PostMapping
    public ResponseEntity<Object> getAllPerson(@RequestBody String query) {
        requestBody = query;
        ExecutionResult execute = graphQLService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
