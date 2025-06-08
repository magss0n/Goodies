package cm.sji.goodies.Model.ServiceImpl;

import cm.sji.goodies.Model.DTO.ProductDTO;
import cm.sji.goodies.Model.Entities.Category;
import cm.sji.goodies.Model.Entities.Product;
import cm.sji.goodies.Model.Repository.ProductRepository;
import cm.sji.goodies.Model.Services.CategoryService;
import cm.sji.goodies.Model.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::mapToDTO);
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::mapToDTO);
    }

//    @Override
//    public List<ProductDTO> getProductsByCategory(String category) {
//        return productRepository.findByCategory(category).stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) {
        return productRepository.searchByKeyword(keyword).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateProductStock(Long productId, Integer newQuantity) {
        productRepository.findById(productId)
                .ifPresent(product -> {
                    product.setQuantity(newQuantity);
                    productRepository.save(product);
                });
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getLowStockProducts(Integer threshold) {
        return productRepository.findLowStockProducts(threshold).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElse(null);
    }

    // Helper methods for mapping between Entity and DTO
    private ProductDTO mapToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .size(product.getSize())
                .type(product.getType())
                .minQuantity(product.getMinQuantity())
                .build();
    }

    private Product mapToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setType(productDTO.getType());
        product.setSize(productDTO.getSize());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setCategory(productDTO.getCategory());
        product.setQuantity(productDTO.getQuantity());
        product.setMinQuantity(productDTO.getMinQuantity());
        return product;
    }


}
