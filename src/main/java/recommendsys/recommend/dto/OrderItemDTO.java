package recommendsys.recommend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemDTO {

    private String productName;
    private Integer quantity;
    private Double totalPrice;
}
