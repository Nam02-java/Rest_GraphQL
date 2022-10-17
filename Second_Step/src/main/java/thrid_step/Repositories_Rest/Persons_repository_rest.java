package thrid_step.Repositories_Rest;

import org.springframework.data.jpa.repository.JpaRepository;
import thrid_step.Model_Rest.Person_model_rest;

public interface Persons_repository_rest extends JpaRepository<Person_model_rest,Long> {
}
