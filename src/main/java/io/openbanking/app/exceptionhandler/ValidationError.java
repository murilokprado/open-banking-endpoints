package io.openbanking.app.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe que representa um erro de validação.
 */
@Getter
@Setter
@AllArgsConstructor
public class ValidationError {

    private String field;
    private Object value;
    private String message;

    public ValidationError(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
