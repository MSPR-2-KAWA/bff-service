package fr.epsi.service.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Product {

    Integer id;
    LocalDateTime createdAt;
    String name;
    Float price;
    String description;
    String color;
    Integer stock;

    @JsonCreator
    public Product(
            @JsonProperty("id") @NonNull Integer id,
            @JsonProperty("createdAt") @NonNull LocalDateTime createdAt,
            @JsonProperty("name") @NonNull String name,
            @JsonProperty("price") @NonNull Float price,
            @JsonProperty("description") @NonNull String description,
            @JsonProperty("color") @NonNull String color,
            @JsonProperty("stock") @NonNull Integer stock) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.price = price;
        this.description = description;
        this.color = color;
        this.stock = stock;
    }
}
