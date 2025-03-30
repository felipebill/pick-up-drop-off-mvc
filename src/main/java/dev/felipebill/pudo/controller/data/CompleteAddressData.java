package dev.felipebill.pudo.controller.data;

import dev.felipebill.pudo.model.Address;

public record CompleteAddressData(Long id, String street, Integer number, String neighborhood, String postalCode,
		String state, String country) {

	public static CompleteAddressData fromAddress(final Address address) {
		return new CompleteAddressData(address.getId(), address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getPostalCode().getValue(), address.getState().toString(), address.getCountry().toString());
	}
}
