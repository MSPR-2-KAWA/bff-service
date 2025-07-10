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
    private String firstname;

    @NonNull
    private String lastname;

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
            @JsonProperty("firstname") @NonNull String firstname,
            @JsonProperty("lastname") @NonNull String lastname,
            @JsonProperty("postalCode") @NonNull String postalCode,
            @JsonProperty("city") @NonNull String city,
            @JsonProperty("companyName") @NonNull String companyName) {
        this.id = id;
        this.createdAt = createdAt;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.postalCode = postalCode;
        this.city = city;
        this.companyName = companyName;
    }
}
