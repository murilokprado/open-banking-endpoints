package io.openbanking.app.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ValidationErrorResponse validationError = new ValidationErrorResponse();

        if (ex != null) {
            List<ValidationError> errors = ex.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map((ObjectError objectError) -> {
                        if (objectError instanceof FieldError) {
                            FieldError fieldError = (FieldError) objectError;

                            return new ValidationError(fieldError.getField(),
                                    fieldError.getRejectedValue(),
                                    fieldError.getDefaultMessage());
                        }

                        return new ValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
                    })
                    .collect(Collectors.toList());

            validationError.setErrors(errors);
        }

        return ResponseEntity.badRequest().body(validationError);
    }
}
