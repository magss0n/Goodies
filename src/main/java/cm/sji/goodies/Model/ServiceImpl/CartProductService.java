package cm.sji.goodies.Model.ServiceImpl;

import cm.sji.goodies.Model.Entities.CartProduct;
import cm.sji.goodies.Model.Repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductService {

    private final CartProductRepository cartProductRepository;

    @Autowired
    public CartProductService(CartProductRepository cartProductRepository) {
        this.cartProductRepository = cartProductRepository;
    }

    public CartProduct saveCartProduct(CartProduct cartProduct) {
        return cartProductRepository.save(cartProduct);
    }

    public CartProduct getCartProductById(Long id) {
        return cartProductRepository.findById(id).orElse(null);
    }

    public List<CartProduct> getAllCartProducts() {
        return cartProductRepository.findAll();
    }

    public List<CartProduct> getCartProductsByOrderId(Long orderId) {
        return cartProductRepository.findAllByOrder_Id(orderId);
    }

}
