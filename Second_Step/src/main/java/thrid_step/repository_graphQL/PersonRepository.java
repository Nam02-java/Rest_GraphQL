package thrid_step.repository_graphQL;

import org.springframework.data.jpa.repository.JpaRepository;
import thrid_step.model_graphQL.Person_graphql;

public interface PersonRepository extends JpaRepository<Person_graphql, String> {
}

