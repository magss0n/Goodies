package cm.sji.goodies.Model.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    String imageUrl;

    @OneToMany(mappedBy = "category")
    List<Product> products;


}
