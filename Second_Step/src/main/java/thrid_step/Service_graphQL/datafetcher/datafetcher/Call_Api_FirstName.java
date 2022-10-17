package thrid_step.Service_graphQL.datafetcher.datafetcher;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static thrid_step.Service_graphQL.datafetcher.datafetcher.AllPersonsDataFetcher.sum_list;


public class Call_Api_FirstName implements Runnable {

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList> responseEntity = restTemplate.getForEntity("http://localhost:8019/getFirstName", ArrayList.class);
        for (int i = 0; i < responseEntity.getBody().size(); i++) {
            sum_list.add(new convert_data_graphQL_to_rest());
            sum_list.get(i).setName(String.valueOf(responseEntity.getBody().get(i)));
        }
    }
}

