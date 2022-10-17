package thrid_step.Service_graphQL.datafetcher;

import com.example.Second_Step.model.Person;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.TypeResolver;
import graphql.schema.idl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import thrid_step.Model_Rest.Person_model_rest;
import thrid_step.Repositories_Rest.Persons_repository_rest;
import thrid_step.Service_graphQL.datafetcher.datafetcher.AllPersonsDataFetcher;
import thrid_step.Service_graphQL.datafetcher.datafetcher.PersonDataFetcher;
import thrid_step.model_graphQL.Person_graphql;
import thrid_step.repository_graphQL.PersonRepository;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Configuration //new update
@Service

public class GraphQLService {

    @Autowired
    private Persons_repository_rest persons_repository_rest;
    private static final Logger logger = LoggerFactory.getLogger(GraphQLService.class);

    /**
     * -------------------------------------------------------------------------------
     */

    @Autowired
    PersonRepository personRepository;

    @Value("classpath:persons.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllPersonsDataFetcher allPersonsDataFetcher;
    @Autowired
    private PersonDataFetcher personDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {

        loadDataIntoHSQL();
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();

    }

    private void loadDataIntoHSQL() {
        persons_repository_rest.save(new Person_model_rest("nam", "truong", "55loduc", "A"));
        persons_repository_rest.save(new Person_model_rest("hai", "dang", "222nguyenphuclai", "B"));
        persons_repository_rest.save(new Person_model_rest("hung", "nguyen", "11tranquoctoan", "A"));
   //     for (int i = 0; i < persons_repository_rest.findAll().size(); i++) {
   //         Stream.of(new Person_graphql(persons_repository_rest.findAll().get(i).getFirst_name(), persons_repository_rest.findAll().get(i).getLast_name(),
   //                 persons_repository_rest.findAll().get(i).getAddress(), persons_repository_rest.findAll().get(i).getMark())).forEach(person_graphql -> {
   //             personRepository.save(person_graphql);
   //         });
  //      }

        //tạm thời block
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allPersons", allPersonsDataFetcher)
                        .dataFetcher("person", personDataFetcher)).type("Data", typeWriting ->
                        typeWriting
                )
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
