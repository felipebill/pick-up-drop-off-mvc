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
public class AddressValidationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnBadRequestWhenStreetIsEmpty() {
        var form = new NewAddressForm("", 123, "ne", "81070-001", State.ACRE.toString(), Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenStreetExceedsMaxLength() {
        var form = new NewAddressForm("a".repeat(101), 123, "ne", "81070-001", State.ACRE.toString(), Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenNumberIsNull() {
        var form = new NewAddressForm("streetname", null, "ne", "81070-001", State.ACRE.toString(), Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenNumberIsNotPositive() {
        var form = new NewAddressForm("streetname", -1, "ne", "81070-001", State.ACRE.toString(), Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenNeighborhoodIsEmpty() {
        var form = new NewAddressForm("streetname", 123, "", "81070-001", State.ACRE.toString(), Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenNeighborhoodExceedsMaxLength() {
        var form = new NewAddressForm("streetname", 123, "a".repeat(51), "81070-001", State.ACRE.toString(), Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenPostalCodeIsEmpty() {
        var form = new NewAddressForm("streetname", 123, "ne", "", State.ACRE.toString(), Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenPostalCodeHasInvalidFormat() {
        var form = new NewAddressForm("streetname", 123, "ne", "123456789", State.ACRE.toString(), Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenStateIsEmpty() {
        var form = new NewAddressForm("streetname", 123, "ne", "81070-001", "", Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenStateIsInvalid() {
        var form = new NewAddressForm("streetname", 123, "ne", "81070-001", "INVALID_STATE", Country.BRASIL.toString());
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenCountryIsEmpty() {
        var form = new NewAddressForm("streetname", 123, "ne", "81070-001", State.ACRE.toString(), "");
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldReturnBadRequestWhenCountryIsInvalid() {
        var form = new NewAddressForm("streetname", 123, "ne", "81070-001", State.ACRE.toString(), "INVALID_COUNTRY");
        ResponseEntity<String> response = restTemplate.postForEntity("/addresses", form, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
