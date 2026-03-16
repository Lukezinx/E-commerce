package lucas.ecommerce.Repository;

import lucas.ecommerce.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}
