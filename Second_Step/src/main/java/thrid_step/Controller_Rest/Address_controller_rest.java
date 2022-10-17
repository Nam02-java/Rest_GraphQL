package thrid_step.Controller_Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thrid_step.Repositories_Rest.Persons_repository_rest;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/getAddress")
public class Address_controller_rest {

    public static Integer time_response_address = 2000;

    @Autowired
    private Persons_repository_rest persons_repository_rest;
    @GetMapping("")
    List<String> getAll_Address() throws InterruptedException {
        List<String> list_address = new ArrayList<>();
        for (int i = 0; i < persons_repository_rest.findAll().size(); i++) {
            list_address.add(persons_repository_rest.findAll().get(i).getAddress());
        }
        Thread.sleep(time_response_address);
        return list_address;
    }
}
