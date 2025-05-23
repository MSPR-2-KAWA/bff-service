package fr.epsi.service.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Customer {

    @NonNull
    private Integer id;

    @NonNull
    private LocalDateTime createdAt;

    @NonNull
    private String username;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String postalCode;

    @NonNull
    private String city;

    @NonNull
    private String companyName;

    @JsonCreator
    public Customer(
            @JsonProperty("id") @NonNull Integer id,
            @JsonProperty("createdAt") @NonNull LocalDateTime createdAt,
            @JsonProperty("username") @NonNull String username,
            @JsonProperty("firstName") @NonNull String firstName,
            @JsonProperty("lastName") @NonNull String lastName,
            @JsonProperty("postalCode") @NonNull String postalCode,
            @JsonProperty("city") @NonNull String city,
            @JsonProperty("companyName") @NonNull String companyName) {
        this.id = id;
        this.createdAt = createdAt;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
        this.city = city;
        this.companyName = companyName;
    }
}
