package cm.sji.goodies.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason")
    private String reason;

    @Column
    private String status;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @Column(name = "qty")
    private Integer qty;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Supplier supplier;

}
