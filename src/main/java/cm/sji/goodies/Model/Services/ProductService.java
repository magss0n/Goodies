package cm.sji.goodies.Model.Services;

import cm.sji.goodies.Model.DTO.ProductDTO;
import cm.sji.goodies.Model.Entities.Category;
import cm.sji.goodies.Model.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);

    List<Product> getProductsByTypeCategory(String type, Long categoryId);

    List<ProductDTO> getAllProducts();

    Page<ProductDTO> getAllProducts(Pageable pageable);

    Optional<ProductDTO> getProductById(Long id);

//    List<ProductDTO> getProductsByCategory(String category);

    List<ProductDTO> searchProducts(String keyword);

    List<ProductDTO> getProductsByPriceRange(Double minPrice, Double maxPrice);

    void updateProductStock(Long productId, Integer newQuantity);

    void deleteProduct(Long id);

    List<ProductDTO> getLowStockProducts(Integer threshold);

    Product getProductByName(String name);

    Product saveProduct(Product product);

    List<Product> getProductsByCategory(String categoryName);

    Product findById(long id);


}