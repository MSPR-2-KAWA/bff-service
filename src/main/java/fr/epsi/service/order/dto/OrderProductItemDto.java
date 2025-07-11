package fr.epsi.service.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductItemDto {

    private Integer productId;
    private Integer quantity;
}
