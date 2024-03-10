package ch.zkmf2024.server.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> methodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {
        log.error("method argument not valid: {} {}", req.getMethod(), req.getRequestURI());
        return ResponseEntity.of(ex.getBody()).build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ProblemDetail> httpRequestMethodNotSupported(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {
        log.error("request method not supported: {} {}", req.getMethod(), req.getRequestURI());
        return ResponseEntity.of(ex.getBody()).build();
    }

}
