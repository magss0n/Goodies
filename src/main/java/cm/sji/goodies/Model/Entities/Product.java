package cm.sji.goodies.Model.Entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5)
    private String size;
    private String name;
    private String description;
    private Double sellPrice;
    private Double costPrice;
//    private String category;
    private Integer quantity;
    private Integer minQuantity;
    private String type;

    @OneToMany(mappedBy = "product")
    private Set<CartProduct> cartProducts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Picture> pictures;

    @ManyToMany
    @JoinTable(
            name = "supplier_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    Set<Supplier> suppliers;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases;

    @Column(name = "creation_time")
    @CreationTimestamp
    private LocalDateTime creationTime;

    private String imageUrl;

}
