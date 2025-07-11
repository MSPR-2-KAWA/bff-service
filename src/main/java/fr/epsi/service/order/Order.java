package fr.epsi.service.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class Order {
    Integer id;
    LocalDateTime createdAt;
    Integer customerId;
    List<OrderProductItem> items;

    @JsonCreator
    public Order(
            @JsonProperty("id") @NonNull Integer id,
            @JsonProperty("createdAt") @NonNull LocalDateTime createdAt,
            @JsonProperty("customerId") @NonNull Integer customerId,
            @JsonProperty("items") @NonNull List<OrderProductItem> items) {
        this.id = id;
        this.createdAt = createdAt;
        this.customerId = customerId;
        this.items = items;
    }
}
