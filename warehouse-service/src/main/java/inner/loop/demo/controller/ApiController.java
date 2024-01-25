package inner.loop.demo.controller;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import inner.loop.demo.entity.Warehouse;
import inner.loop.demo.repository.WarehouseRepository;
import jakarta.annotation.Resource;

@RestController
public class ApiController {

    private final AtomicInteger stock = new AtomicInteger(10);

    @Resource
    private WarehouseRepository repository;

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

    @PutMapping("/stock/db/{quantity}")
    public ResponseEntity orderDb(@PathVariable int quantity) {
        Optional<Warehouse> result = repository.findById(1L);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Warehouse warehouse = result.get();
        int current = warehouse.getQuantity();
        int newValue = current - quantity;

        if (newValue < 0) {
            return ResponseEntity.badRequest().body("The quantity is greater than stock quantity!");
        }

        warehouse.setQuantity(newValue);
        repository.save(warehouse);

        return ResponseEntity.ok().body(newValue);
    }

    @PutMapping("/stock/db/reset")
    public ResponseEntity<Integer> resetDb() {
        Optional<Warehouse> result = repository.findById(1L);
        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Warehouse warehouse = result.get();
        warehouse.setQuantity(10);
        repository.save(warehouse);

        return ResponseEntity.ok().body(10);
    }
}
