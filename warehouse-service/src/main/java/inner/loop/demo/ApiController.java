package inner.loop.demo;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final AtomicInteger stock = new AtomicInteger(10);

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PutMapping("/stock/{quantity}")
    public ResponseEntity order(@PathVariable int quantity) {
        int current = stock.get();
        int newValue = current - quantity;

        if (newValue < 0) {
            return ResponseEntity.badRequest().body("The quantity is greater than stock quantity!");
        }

        stock.set(newValue);
        return ResponseEntity.ok().body(newValue);
    }

    @PutMapping("/stock/reset")
    public int reset() {
        stock.set(10);
        return 10;
    }
}
