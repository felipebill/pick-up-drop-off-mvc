package dev.felipebill.pudo.controller;

import dev.felipebill.pudo.model.Address;

public record NewlyRegisteredAddressData(Long id, String street, Integer number, String neighborhood, String postalCode,
		String state, String country) {

	public static NewlyRegisteredAddressData fromAddress(final Address address) {
		return new NewlyRegisteredAddressData(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getPostalCode().getValue(), address.getState().toString(), address.getCountry().toString());
	}
}
