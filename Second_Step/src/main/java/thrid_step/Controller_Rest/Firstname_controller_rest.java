package thrid_step.Controller_Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thrid_step.Repositories_Rest.Persons_repository_rest;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/getFirstName")
public class Firstname_controller_rest {

    public static Integer time_response_firstname = 1000;

    @Autowired
    private Persons_repository_rest persons_repository_rest;

    @GetMapping("")
    List<String> getAll_FirstName() throws InterruptedException {
        List<String> list_first_name = new ArrayList<>();
        for (int i = 0; i < persons_repository_rest.findAll().size(); i++) {
            list_first_name.add(persons_repository_rest.findAll().get(i).getFirst_name());
        }
        Thread.sleep(time_response_firstname);
        return list_first_name;
    }
}

// try {
//                list_first_name.add(persons_repository_rest.findAll().get(5).getFirst_name());
//            } catch (NullPointerException e) {
//                System.out.println(e);
//            }