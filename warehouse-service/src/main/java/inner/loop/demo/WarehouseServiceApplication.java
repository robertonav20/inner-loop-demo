package inner.loop.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class WarehouseServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(WarehouseServiceApplication.class, args);
  }
}
