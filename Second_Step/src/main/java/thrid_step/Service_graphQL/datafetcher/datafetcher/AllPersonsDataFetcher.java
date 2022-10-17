package thrid_step.Service_graphQL.datafetcher.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import thrid_step.repository_graphQL.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Component
public class AllPersonsDataFetcher implements DataFetcher<List<convert_data_graphQL_to_rest>> {

    @Autowired
    PersonRepository personRepository;
    public static List<convert_data_graphQL_to_rest> sum_list = new ArrayList<>();
    private Call_Api_FirstName call_api_firstName = new Call_Api_FirstName();
    private Call_Api_LastName call_api_lastName = new Call_Api_LastName();
    private Call_Api_Address call_api_address = new Call_Api_Address();
    private Call_Api_Mark call_api_mark = new Call_Api_Mark();

    @Override
    public List<convert_data_graphQL_to_rest> get(DataFetchingEnvironment dataFetchingEnvironment) throws InterruptedException {
        sum_list.clear();
        Thread thread_firstname = new Thread(call_api_firstName);
        Thread thread_lastname = new Thread(call_api_lastName);
        Thread thread_address = new Thread(call_api_address);
        Thread thread_mark = new Thread(call_api_mark);

        try {
            thread_firstname.start();
            thread_firstname.join(500);
            thread_lastname.start();
            thread_address.start();
            thread_mark.start();
        } catch (IllegalThreadStateException e) {
            System.out.println("Error : " + e);
        }
        Thread.sleep(3500);

        check_data_null();
        return sum_list;
    }


    private void check_data_null() {
        for (int i = 0; i < sum_list.size(); i++) {
            if (sum_list.get(i).getName() == (null) && sum_list.get(i).getLast() == (null)
                    && sum_list.get(i).getAddress() == (null) && sum_list.get(i).getMark() == (null)) {
                sum_list.remove(i);
                i -= 1;
            }
        }
    }
}




class convert_data_graphQL_to_rest {
    private String isn;
    private String name;
    private String last;
    private String address;
    private String mark;

    public convert_data_graphQL_to_rest() {
    }


    public convert_data_graphQL_to_rest(String isn) {
        this.isn = isn;
    }

    public convert_data_graphQL_to_rest(String isn, String name) {
        this.isn = isn;
        this.name = name;
    }

    public convert_data_graphQL_to_rest(String isn, String name, String last) {
        this.isn = isn;
        this.name = name;
        this.last = last;
    }

    public convert_data_graphQL_to_rest(String isn, String name, String last, String address) {
        this.isn = isn;
        this.name = name;
        this.last = last;
        this.address = address;
    }

    public convert_data_graphQL_to_rest(String isn, String name, String last, String address, String mark) {
        this.isn = isn;
        this.name = name;
        this.last = last;
        this.address = address;
        this.mark = mark;
    }

    public String getIsn() {
        return isn;
    }

    public void setIsn(String isn) {
        this.isn = isn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}



