package thrid_step.Service_graphQL.datafetcher.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import thrid_step.model_graphQL.Person_graphql;
import thrid_step.repository_graphQL.PersonRepository;

@Component
public class PersonDataFetcher implements DataFetcher<Person_graphql> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person_graphql get(DataFetchingEnvironment dataFetchingEnvironment) {
        String isn = dataFetchingEnvironment.getArgument("id");
        return personRepository.findById(isn).get();
       // return personRepository.findById(isn).get();
    }
}

