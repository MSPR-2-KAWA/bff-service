package fr.epsi.service.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

@Value
public class OrderProductItem {
    Integer productId;
    Integer quantity;

    @JsonCreator
    public OrderProductItem(
            @JsonProperty("productId") @NonNull Integer productId,
            @JsonProperty("quantity") @NonNull Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}