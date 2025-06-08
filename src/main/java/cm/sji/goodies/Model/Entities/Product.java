package cm.sji.goodies.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Double unitPrice;
//    private String category;
    private Integer quantity;
    private Integer minQuantity;
    private String type;

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

}
