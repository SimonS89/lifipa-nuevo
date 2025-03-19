package com.test.lifipa.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*@RestControllerAdvice
public class AppExceptionHandler {

    private static final String ACCESS_DENIED = "access_denied_reason";
    private static final String ERROR_MESSAGE = "errorMessage";

    private static String bdUniqueMsg(String errorMessage) {
        String customErrorMessage = "";
        if (errorMessage.contains("CUIT")) {
            customErrorMessage = "El CUIT del proveedor debe ser único. Por favor, elija otro CUIT.";
        } else if (errorMessage.contains("CODIGO")) {
            customErrorMessage = "El código del proveedor debe ser único. Por favor, elija otro código.";
        }
        return customErrorMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArguments(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> resourceNotFound(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put(ERROR_MESSAGE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> alreadyExists(AlreadyExistsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put(ERROR_MESSAGE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> uniqueException(DataIntegrityViolationException ex) {
        String errorMessage = ex.getRootCause().getMessage();
        String customErrorMessage = bdUniqueMsg(errorMessage);

        Map<String, String> error = new HashMap<>();
        error.put(ERROR_MESSAGE, customErrorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex) {
        ProblemDetail errorDetail = null;
        if (ex instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty(ACCESS_DENIED, "Credenciales incorrectas");
        }
        if (ex instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty(ACCESS_DENIED, "Autorización denegada");
        }
        if (ex instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty(ACCESS_DENIED, "Token inválido");
        }
        if (ex instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty(ACCESS_DENIED, "Token expirado");
        }
        return errorDetail;
    }
}*/

@RestControllerAdvice
public class AppExceptionHandler {

    private static final String ACCESS_DENIED = "access_denied_reason";

    private ProblemDetail createProblemDetail(HttpStatusCode status, String detail, String reason) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(status, detail);
        errorDetail.setProperty(ACCESS_DENIED, reason);
        return errorDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleInvalidArguments(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Validation errors: ");
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
        );
        return createProblemDetail(HttpStatusCode.valueOf(400), errorMessage.toString(), "Invalid Arguments");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail resourceNotFound(ResourceNotFoundException ex) {
        return createProblemDetail(HttpStatusCode.valueOf(404), ex.getMessage(), "Resource Not Found");
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ProblemDetail alreadyExists(AlreadyExistsException ex) {
        return createProblemDetail(HttpStatusCode.valueOf(400), ex.getMessage(), "Already Exists");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail uniqueException(DataIntegrityViolationException ex) {
        String customErrorMessage = bdUniqueMsg(ex.getRootCause().getMessage());
        return createProblemDetail(HttpStatusCode.valueOf(400), customErrorMessage, "Data Integrity Violation");
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex) {
        if (ex instanceof BadCredentialsException) {
            return createProblemDetail(HttpStatusCode.valueOf(401), ex.getMessage(), "Credenciales incorrectas");
        }
        if (ex instanceof AccessDeniedException) {
            return createProblemDetail(HttpStatusCode.valueOf(403), ex.getMessage(), "Autorización denegada");
        }
        if (ex instanceof SignatureException) {
            return createProblemDetail(HttpStatusCode.valueOf(403), ex.getMessage(), "Token inválido");
        }
        if (ex instanceof ExpiredJwtException) {
            return createProblemDetail(HttpStatusCode.valueOf(403), ex.getMessage(), "Token expirado");
        }
        return createProblemDetail(HttpStatusCode.valueOf(500), ex.getMessage(), "Internal Server Error");
    }

    private static String bdUniqueMsg(String errorMessage) {
        if (errorMessage.contains("CUIT")) {
            return "El CUIT del proveedor debe ser único. Por favor, elija otro CUIT.";
        } else if (errorMessage.contains("CODIGO")) {
            return "El código del proveedor debe ser único. Por favor, elija otro código.";
        }
        return "Data integrity violation";
    }
}

