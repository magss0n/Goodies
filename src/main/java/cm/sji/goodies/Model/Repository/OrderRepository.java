package cm.sji.goodies.Model.Repository;

import cm.sji.goodies.Model.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
