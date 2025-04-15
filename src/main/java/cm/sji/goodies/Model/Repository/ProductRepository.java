package cm.sji.goodies.Model.Repository;

import cm.sji.goodies.Model.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
