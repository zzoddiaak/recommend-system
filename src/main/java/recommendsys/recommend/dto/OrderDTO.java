package recommendsys.recommend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {

    private Long orderId;
    private LocalDateTime orderDate;
    private String userName;  // Добавим имя пользователя
    private List<OrderItemDTO> orderItems;
    private Double totalPrice;

    // Метод для форматированного вывода даты
    public String getFormattedOrderDate() {
        return orderDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy, h:mm:ss a"));
    }
}
