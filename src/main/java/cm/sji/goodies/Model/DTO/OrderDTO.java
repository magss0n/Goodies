package cm.sji.goodies.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long clientId;
    private String clientName;
    private LocalDate purchaseDate;
    private String status;
    private List<CartProductDTO> products;
    private Double totalAmount;
}