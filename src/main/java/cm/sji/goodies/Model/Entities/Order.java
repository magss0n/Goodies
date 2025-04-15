package cm.sji.goodies.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Client client;

    private LocalDate purchaseDate;
    private String status;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class CartProduct {

        @OneToOne
        private Product product;
        private Integer selectedQty;
        private Double total;
    }



}
