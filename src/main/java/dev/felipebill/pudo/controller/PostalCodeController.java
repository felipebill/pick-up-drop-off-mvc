package dev.felipebill.pudo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.felipebill.pudo.service.PostalCodeData;
import dev.felipebill.pudo.service.PostalCodeWSClient;

@RestController
@RequestMapping("/postalCodes")
public class PostalCodeController {

	private final PostalCodeWSClient postalCodeWSClient;

	PostalCodeController(PostalCodeWSClient postalCodeWSClient) {
		this.postalCodeWSClient = postalCodeWSClient;
	}

	@GetMapping
	public ResponseEntity<PostalCodeData> findbyPostalCode(@RequestParam String postalCodeValue) {
		PostalCodeData postalCodeData = this.postalCodeWSClient.findPostalCode(postalCodeValue);
		return ResponseEntity.ok(postalCodeData);
	}

}
