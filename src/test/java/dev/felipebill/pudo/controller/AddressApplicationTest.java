package dev.felipebill.pudo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.felipebill.pudo.model.Country;
import dev.felipebill.pudo.model.State;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressApplicationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnNewlyRegisteredAddressDataWithId() {
        var form = new RegisterNewAddressForm("streetname", 123, "ne", "81070-001", State.ACRE.toString(), Country.BRASIL.toString());
        ResponseEntity<NewlyRegisteredAddressData> response = restTemplate.postForEntity("/addresses", form, NewlyRegisteredAddressData.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isNotNull();
        assertThat(response.getBody().street()).isEqualTo(form.street());
        assertThat(response.getBody().number()).isEqualTo(form.number());
        assertThat(response.getBody().neighborhood()).isEqualTo(form.neighborhood());
        assertThat(response.getBody().postalCode()).isEqualTo(form.postalCode());
        assertThat(response.getBody().state()).isEqualTo(form.state());
        assertThat(response.getBody().country()).isEqualTo(form.country());
    }
    
    
}
