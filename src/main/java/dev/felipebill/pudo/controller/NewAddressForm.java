package dev.felipebill.pudo.controller;

import dev.felipebill.pudo.model.Address;
import dev.felipebill.pudo.model.Country;
import dev.felipebill.pudo.model.PostalCode;
import dev.felipebill.pudo.model.State;

import jakarta.validation.constraints.*;

public record NewAddressForm(
        @NotBlank(message = "Street cannot be blank")
        @Size(max = 100, message = "Street must not exceed 100 characters")
        String street,

        @NotNull(message = "Number cannot be null")
        @Positive(message = "Number must be positive")
        Integer number,

        @NotBlank(message = "Neighborhood cannot be blank")
        @Size(max = 50, message = "Neighborhood must not exceed 50 characters")
        String neighborhood,

        @NotBlank(message = "Postal code cannot be blank")
        @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Postal code must follow the format XXXXX-XXX")
        String postalCode,

        @NotBlank(message = "State cannot be blank")
        @Pattern(regexp = "AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO",
                 message = "State must be a valid Brazilian state abbreviation")
        String state,

        @NotBlank(message = "Country cannot be blank")
        @Pattern(regexp = "BRASIL", message = "Country must be 'BRASIL'")
        String country
) {

    Address toAddress() {
        var state = State.valueOf(this.state());
        var country = Country.valueOf(this.country());
        var postalCode = new PostalCode(this.postalCode());
        Address address = Address.builder()
                .street(street)
                .number(number)
                .neighborhood(neighborhood)
                .postalCode(postalCode)
                .state(state)
                .country(country)
                .build();
        return address;
    }
}
