package dev.felipebill.pudo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class PostalCode {

	@NotBlank(message = "PostalCode can't be null nor blank")
	@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "PostalCode must have this format XXXXX-XXX")
	@Column(name = "DS_POSTAL_CODE")
	private String value;

	public PostalCode() {
		
	}
	
	// Construtor
	public PostalCode(String value) {
		if (!isValid(value)) {
			throw new IllegalArgumentException("Invalid PostalCode");
		}
		this.value = value;
	}

	private boolean isValid(String cep) {
		if (!cep.matches("^\\d{5}-\\d{3}$")) {
			return false;
		}

		String digits = cep.replace("-", "");
		if (digits.chars().distinct().count() == 1) {
			return false;
		}

		return true;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if (!isValid(value)) {
			throw new IllegalArgumentException("Invalid PostalCode");
		}
		this.value = value;
	}
}
