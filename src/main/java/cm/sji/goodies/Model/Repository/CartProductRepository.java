package cm.sji.goodies.Model.Repository;

import cm.sji.goodies.Model.Entities.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    List<CartProduct> findAllByOrder_Id(Long orderId);
}
