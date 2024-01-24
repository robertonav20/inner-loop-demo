package inner.loop.demo;

import java.net.URI;
import java.net.http.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
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
    public ResponseEntity<Integer> order(@PathVariable int quantity) {
        UriComponents uriComponents =
                UriComponentsBuilder.newInstance().scheme("http").host("warehouse-service")
                        .port("8001").path("/stock/{quantity}").buildAndExpand(quantity);

        try {
            return ResponseEntity.created(uriComponents.toUri()).body(
                    restClient.put().uri(uriComponents.toUri()).retrieve().body(Integer.class));
        } catch (HttpClientErrorException e) {
            return ResponseEntity.badRequest().body(-1);
        }
    }
}
