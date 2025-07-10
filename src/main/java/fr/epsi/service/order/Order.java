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
    List<Integer> productIds;

    @JsonCreator
    public Order(
            @JsonProperty("id") @NonNull Integer id,
            @JsonProperty("createdAt") @NonNull LocalDateTime createdAt,
            @JsonProperty("customerId") @NonNull Integer customerId,
            @JsonProperty("productIds") @NonNull List<Integer> productIds){
        this.id = id;
        this.createdAt = createdAt;
        this.customerId = customerId;
        this.productIds = productIds;
    }
}
