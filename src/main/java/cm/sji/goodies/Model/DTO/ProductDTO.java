package cm.sji.goodies.Model.DTO;

import cm.sji.goodies.Model.Entities.Category;
import cm.sji.goodies.Model.Entities.Picture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double sellPrice;
    private Double costPrice;
    private Category category;
    private Integer quantity;
    private String size;
    private Integer minQuantity;
    private String type;
    private LocalDateTime creationTime;
    private String imageUrl;

}