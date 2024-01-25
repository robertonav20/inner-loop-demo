package inner.loop.demo.repository;

import org.springframework.data.repository.CrudRepository;
import inner.loop.demo.entity.Warehouse;

public interface WarehouseRepository extends CrudRepository<Warehouse, Long> {
}
