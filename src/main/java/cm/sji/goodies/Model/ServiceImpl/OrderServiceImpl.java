package cm.sji.goodies.Model.ServiceImpl;

import cm.sji.goodies.Model.DTO.CartProductDTO;
import cm.sji.goodies.Model.DTO.OrderDTO;
import cm.sji.goodies.Model.Entities.Client;
import cm.sji.goodies.Model.Entities.Order;
import cm.sji.goodies.Model.Entities.Product;
import cm.sji.goodies.Model.Repository.ClientRepository;
import cm.sji.goodies.Model.Repository.OrderRepository;
import cm.sji.goodies.Model.Repository.ProductRepository;
import cm.sji.goodies.Model.Services.OrderService;
import cm.sji.goodies.Model.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ClientRepository clientRepository,
                            ProductRepository productRepository,
                            ProductService productService) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @Override
    @Transactional
    public OrderDTO createOrder(Long clientId, List<CartProductDTO> cartProducts) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));

        Order order = new Order();
        order.setClient(client);
        order.setPurchaseDate(LocalDate.now());
        order.setStatus("PENDING");

        Order savedOrder = orderRepository.save(order);

        // Update product inventory
        for (CartProductDTO cartProduct : cartProducts) {
            Product product = productRepository.findById(cartProduct.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + cartProduct.getProductId()));

            // Update stock
            int newQuantity = product.getQuantity() - cartProduct.getSelectedQty();
            if (newQuantity < 0) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }
            productService.updateProductStock(product.getId(), newQuantity);
        }

        return mapToDTO(savedOrder, cartProducts);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> {
                    // In a real application, you would fetch the cart products from a separate table
                    // Here we're just creating an empty list as a placeholder
                    List<CartProductDTO> cartProducts = new ArrayList<>();
                    return mapToDTO(order, cartProducts);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    // In a real application, you would fetch the cart products from a separate table
                    List<CartProductDTO> cartProducts = new ArrayList<>();
                    return mapToDTO(order, cartProducts);
                });
    }

    @Override
    public List<OrderDTO> getOrdersByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId).stream()
                .map(order -> {
                    List<CartProductDTO> cartProducts = new ArrayList<>();
                    return mapToDTO(order, cartProducts);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status).stream()
                .map(order -> {
                    List<CartProductDTO> cartProducts = new ArrayList<>();
                    return mapToDTO(order, cartProducts);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findByPurchaseDateBetween(startDate, endDate).stream()
                .map(order -> {
                    List<CartProductDTO> cartProducts = new ArrayList<>();
                    return mapToDTO(order, cartProducts);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);

        // In a real application, you would fetch the cart products from a separate table
        List<CartProductDTO> cartProducts = new ArrayList<>();
        return mapToDTO(updatedOrder, cartProducts);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Long countOrdersByStatus(String status) {
        return orderRepository.countByStatus(status);
    }

    // Helper method for mapping between Entity and DTO
    private OrderDTO mapToDTO(Order order, List<CartProductDTO> cartProducts) {
        double totalAmount = cartProducts.stream()
                .mapToDouble(CartProductDTO::getTotal)
                .sum();

        return OrderDTO.builder()
                .id(order.getId())
                .clientId(order.getClient().getId())
                .clientName(order.getClient().getName())
                .purchaseDate(order.getPurchaseDate())
                .status(order.getStatus())
                .products(cartProducts)
                .totalAmount(totalAmount)
                .build();
    }
}
