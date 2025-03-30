package dev.felipebill.pudo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.felipebill.pudo.controller.data.CompleteAddressData;
import dev.felipebill.pudo.controller.form.NewAddressForm;
import dev.felipebill.pudo.model.Country;
import dev.felipebill.pudo.model.State;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressApplicationTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnNewlyRegisteredAddressDataWithId() {
		var form = new NewAddressForm("streetname", 123, "ne", "81070-001", State.ACRE.toString(),
				Country.BRASIL.toString());
		ResponseEntity<CompleteAddressData> response = restTemplate.postForEntity("/addresses", form,
				CompleteAddressData.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().id()).isNotNull();
		assertThat(response.getBody().street()).isEqualTo(form.street());
		assertThat(response.getBody().number()).isEqualTo(form.number());
		assertThat(response.getBody().neighborhood()).isEqualTo(form.neighborhood());
		assertThat(response.getBody().postalCode()).isEqualTo(form.postalCode());
		assertThat(response.getBody().state()).isEqualTo(form.state());
		assertThat(response.getBody().country()).isEqualTo(form.country());

		String locationHeader = response.getHeaders().getLocation().toString();
		assertThat(locationHeader).isNotNull();
		assertThat(locationHeader).contains("/addresses/" + response.getBody().id());
		Long id = response.getBody().id();
		ResponseEntity<CompleteAddressData> getAddressResponse = restTemplate.getForEntity(locationHeader,
				CompleteAddressData.class);

		assertThat(getAddressResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getAddressResponse.getBody()).isNotNull();
		assertThat(getAddressResponse.getBody().id()).isEqualTo(id);
		assertThat(getAddressResponse.getBody().street()).isEqualTo(form.street());
		assertThat(getAddressResponse.getBody().number()).isEqualTo(form.number());
		assertThat(getAddressResponse.getBody().neighborhood()).isEqualTo(form.neighborhood());
		assertThat(getAddressResponse.getBody().postalCode()).isEqualTo(form.postalCode());
		assertThat(getAddressResponse.getBody().state()).isEqualTo(form.state());
		assertThat(getAddressResponse.getBody().country()).isEqualTo(form.country());
	}

	@Test
	void shouldIncrementIdAfterRegisteringTwice() {
		var form = new NewAddressForm("streetname", 123, "ne", "81070-001", State.ACRE.toString(),
				Country.BRASIL.toString());
		ResponseEntity<CompleteAddressData> response1 = restTemplate.postForEntity("/addresses", form,
				CompleteAddressData.class);
		assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response1.getBody()).isNotNull();
		assertThat(response1.getBody().id()).isNotNull();
		String locationHeader1 = response1.getHeaders().getLocation().toString();
		assertThat(locationHeader1).isNotNull();
		assertThat(locationHeader1).contains("/addresses/" + response1.getBody().id());

		ResponseEntity<CompleteAddressData> response2 = restTemplate.postForEntity("/addresses", form,
				CompleteAddressData.class);
		assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response2.getBody()).isNotNull();
		assertThat(response2.getBody().id()).isNotNull();
		String locationHeader2 = response2.getHeaders().getLocation().toString();
		assertThat(locationHeader2).isNotNull();
		assertThat(locationHeader2).contains("/addresses/" + response2.getBody().id());

		assertThat(response1.getBody().id()).isNotEqualTo(response2.getBody().id());

	}
}
