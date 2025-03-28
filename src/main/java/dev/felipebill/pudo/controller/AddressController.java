package dev.felipebill.pudo.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import dev.felipebill.pudo.model.Address;
import dev.felipebill.pudo.repository.AddressRepository;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	private final AddressRepository addressRepository;

	public AddressController(final AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@GetMapping("/{id}")
	ResponseEntity<CompleteAddressData> findById(@PathVariable Long id) {
		Optional<Address> addressOptional = this.addressRepository.findById(id);
		if (addressOptional.isPresent()) {
			var data = CompleteAddressData.fromAddress(addressOptional.get());
			return ResponseEntity.ok(data);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping
	ResponseEntity<CompleteAddressData> registerNewAddress(@RequestBody NewAddressForm form,
			UriComponentsBuilder uriComponentsBuilder) {
		var address = form.toAddress();
		this.addressRepository.save(address);
		var data = CompleteAddressData.fromAddress(address);

		URI uri = uriComponentsBuilder.path("/addresses/{id}").buildAndExpand(data.id()).toUri();

		return ResponseEntity.created(uri).body(data);
	}

}
