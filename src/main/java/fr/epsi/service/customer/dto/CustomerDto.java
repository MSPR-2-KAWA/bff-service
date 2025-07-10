package fr.epsi.service.customer.dto;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;


@Value
@AllArgsConstructor
public class CustomerDto {

    @NonNull
    String username;

    @NonNull
    String firstName;

    @NonNull
    String lastName;

    @NonNull
    String postalCode;

    @NonNull
    String city;

    @NonNull
    String companyName;

}
