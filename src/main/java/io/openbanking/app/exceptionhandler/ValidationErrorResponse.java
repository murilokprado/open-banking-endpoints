package io.openbanking.app.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Classe contendo a lista de erros de validação.
 */
@Getter
@Setter
public class ValidationErrorResponse {

    private List<ValidationError> errors;
}
