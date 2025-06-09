package cm.sji.goodies.Model.Services;

import cm.sji.goodies.Model.DTO.CartProductDTO;
import cm.sji.goodies.Model.DTO.OrderDTO;
import cm.sji.goodies.Model.Entities.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDTO createOrder(Long clientId, List<CartProductDTO> cartProducts);

    List<OrderDTO> getAllOrders();

    Optional<OrderDTO> getOrderById(Long id);

    List<OrderDTO> getOrdersByClientId(Long clientId);

    List<OrderDTO> getOrdersByStatus(String status);

    List<OrderDTO> getOrdersByDateRange(LocalDate startDate, LocalDate endDate);

    OrderDTO updateOrderStatus(Long orderId, String newStatus);

    void deleteOrder(Long id);

    Long countOrdersByStatus(String status);

    Order saveOrder(Order order);
    List<Order> getAllOrdersByClientId(Long clientId);
    List<Order> getAllOrdersByStatus(String status);
    List<Order> findAllOrders();
    Order getOrderbyId(Long id);
}
