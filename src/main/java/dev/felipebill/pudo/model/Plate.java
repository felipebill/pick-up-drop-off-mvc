package dev.felipebill.pudo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class Plate {

    @NotBlank(message = "Plate can't be null nor blank")
    @Pattern(regexp = "^[A-Z]{3}-\\d[A-Z]\\d{2}$", message = "Plate must have this format AAA-9A99")
    @Column(name = "DS_PLATE")
    private String value;

    public Plate() {
    }

    // Construtor
    public Plate(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Plate");
        }
        this.value = value;
    }

    private boolean isValid(String plate) {
        if (!plate.matches("^[A-Z]{3}-\\d[A-Z]\\d{2}$")) {
            return false;
        }

        // Verifica se todos os caracteres são iguais (exemplo inválido: "AAA-9A99")
        String alphanumeric = plate.replace("-", "");
        if (alphanumeric.chars().distinct().count() == 1) {
            return false;
        }

        return true;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid Plate");
        }
        this.value = value;
    }
}
