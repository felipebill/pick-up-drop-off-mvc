package dev.felipebill.pudo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import dev.felipebill.pudo.model.Country;
import dev.felipebill.pudo.model.State;

@JsonTest
public class AddressJsonTest {

	@Autowired
	private JacksonTester<CompleteAddressData> json;

	@Test
	void completeAddressDataSerializationTest() throws IOException {
	    CompleteAddressData addressData = new CompleteAddressData(
	        1L,
	        "Rua dos Testes",
	        123,
	        "Bairro Exemplo",
	        "12345-678",
	        "ACRE",
	        "Brasil"
	    );

	    System.out.println("Serialized JSON: " + json.write(addressData).getJson());

	    assertThat(json.write(addressData)).isStrictlyEqualToJson("expectedAddressData.json");
	    
	    // Validação de campos
	    assertThat(json.write(addressData)).hasJsonPathNumberValue("@.id");
	    assertThat(json.write(addressData)).extractingJsonPathNumberValue("@.id")
	            .isEqualTo(1);

	    assertThat(json.write(addressData)).hasJsonPathStringValue("@.street");
	    assertThat(json.write(addressData)).extractingJsonPathStringValue("@.street")
	            .isEqualTo("Rua dos Testes");

	    assertThat(json.write(addressData)).hasJsonPathNumberValue("@.number");
	    assertThat(json.write(addressData)).extractingJsonPathNumberValue("@.number")
	            .isEqualTo(123);

	    assertThat(json.write(addressData)).hasJsonPathStringValue("@.neighborhood");
	    assertThat(json.write(addressData)).extractingJsonPathStringValue("@.neighborhood")
	            .isEqualTo("Bairro Exemplo");

	    assertThat(json.write(addressData)).hasJsonPathStringValue("@.postalCode");
	    assertThat(json.write(addressData)).extractingJsonPathStringValue("@.postalCode")
	            .isEqualTo("12345-678");

	    assertThat(json.write(addressData)).hasJsonPathStringValue("@.state");
	    assertThat(json.write(addressData)).extractingJsonPathStringValue("@.state")
	            .isEqualTo("ACRE");

	    assertThat(json.write(addressData)).hasJsonPathStringValue("@.country");
	    assertThat(json.write(addressData)).extractingJsonPathStringValue("@.country")
	            .isEqualTo("Brasil");
	}


}
