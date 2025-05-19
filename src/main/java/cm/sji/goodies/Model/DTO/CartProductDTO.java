package cm.sji.goodies.Model.DTO;

import cm.sji.goodies.Model.Entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {
    private Long productId;
    private String productName;
    private Double unitPrice;
    private Integer selectedQty;
    private Double total;
}