package fr.epsi.service.order.dto;

import lombok.Value;

import java.util.List;

@Value
public class OrderDto {

    Integer customerId;
    List<OrderProductItemDto> items;
}
