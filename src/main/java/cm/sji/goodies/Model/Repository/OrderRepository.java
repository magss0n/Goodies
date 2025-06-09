package cm.sji.goodies.Model.Repository;

import cm.sji.goodies.Model.Entities.Client;
import cm.sji.goodies.Model.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClient(Client client);

    List<Order> findByStatus(String status);

    List<Order> findByPurchaseDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT o FROM Order o WHERE o.client.id = :clientId")
    List<Order> findByClientId(@Param("clientId") Long clientId);

    @Query("SELECT o FROM Order o WHERE o.purchaseDate = :date")
    List<Order> findByPurchaseDate(@Param("date") LocalDate date);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(@Param("status") String status);

    List<Order> findAllByClient_Id(Long clientId);

    List<Order> findAllByStatus(String status);
}