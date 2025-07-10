package fr.epsi.service.product.dto;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;


@Value
@AllArgsConstructor
public class ProductDto {

    @NonNull
    String name;

    @NonNull
    Float price;

    @NonNull
    String description;

    @NonNull
    String color;

    @NonNull
    Integer stock;

}
