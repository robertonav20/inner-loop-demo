package inner.loop.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ApiController {

    RestClient restClient = RestClient.create();

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PutMapping("/order/{quantity}")
    public Integer order(@PathVariable int quantity) {
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance().scheme("http").host("warehouse-service")
                        .port("8001").path("/stock/{quantity}").buildAndExpand(quantity);

        return restClient.put().uri(uriComponents.toUri()).retrieve().body(Integer.class);
    }
}
