package dev.felipebill.pudo.controller;

import dev.felipebill.pudo.model.Address;
import dev.felipebill.pudo.model.Country;
import dev.felipebill.pudo.model.PostalCode;
import dev.felipebill.pudo.model.State;

public record RegisterNewAddressForm(String street, Integer number, String neighborhood, String postalCode,
		String state, String country) {

	Address toAddress() {
		var state = State.valueOf(this.state());
		var country = Country.valueOf(this.country());
		var postalCode = new PostalCode(this.postalCode());
		Address address = Address.builder().street(street).number(number).neighborhood(neighborhood)
				.postalCode(postalCode).state(state).country(country).build();
		return address;
	}

}
