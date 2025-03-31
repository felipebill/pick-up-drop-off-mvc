package dev.felipebill.pudo.configuration;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    // 404 - Entidade não encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex) {
        return buildResponse(
            HttpStatus.NOT_FOUND,
            ex.getMessage(),
            ex
        );
    }

    // 400 - Validação de campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(ValidationError::new)
            .toList();
        
        return buildResponse(
            HttpStatus.BAD_REQUEST,
            "Erro de validação nos campos",
            ex,
            errors
        );
    }

    // 400 - JSON malformado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMalformedJson(HttpMessageNotReadableException ex) {
        return buildResponse(
            HttpStatus.BAD_REQUEST,
            "Requisição JSON malformada",
            ex
        );
    }

    // 405 - Método HTTP não suportado
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return buildResponse(
            HttpStatus.METHOD_NOT_ALLOWED,
            "Método HTTP não suportado para este endpoint",
            ex
        );
    }

    // 409 - Conflito em operações no banco
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return buildResponse(
            HttpStatus.CONFLICT,
            "Conflito na operação: verifique restrições de unicidade ou relacionamentos",
            ex
        );
    }

    // 400 - Tipo de parâmetro incorreto
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("Parâmetro '%s' com tipo inválido: %s", 
            ex.getName(), 
            ex.getRequiredType().getSimpleName());
        
        return buildResponse(
            HttpStatus.BAD_REQUEST,
            message,
            ex
        );
    }

    // 500 - Erros genéricos não tratados
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
    	String message = String.format(ex.getLocalizedMessage());
    	
    	return buildResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            message,
            ex
        );
    }
    
    // 500 - Erros genéricos não tratados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericErrors(Exception ex) {
        return buildResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro interno no servidor",
            ex
        );
    }

    private ResponseEntity<ApiError> buildResponse(HttpStatus status, String message, Exception ex) {
        return buildResponse(status, message, ex, null);
    }

    private ResponseEntity<ApiError> buildResponse(
        HttpStatus status, 
        String message, 
        Exception ex, 
        List<ValidationError> details
    ) {
        ApiError error = new ApiError(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            message,
            ex.getClass().getSimpleName(),
            details
        );
        
        return ResponseEntity.status(status).body(error);
    }

    // Records para estruturação das respostas
    private record ApiError(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String exceptionType,
        List<ValidationError> details
    ) {}

    private record ValidationError(
        String field,
        String message
    ) {
        public ValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
