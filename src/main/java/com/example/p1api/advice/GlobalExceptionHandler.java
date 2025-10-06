package com.example.p1api.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidBody(
            org.springframework.web.bind.MethodArgumentNotValidException ex,
            jakarta.servlet.http.HttpServletRequest request) {

        List<Map<String, String>> fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> Map.of(
                        "name", err.getField(),
                        "message", err.getDefaultMessage()))
                .toList();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "Invalid input");
        body.put("fields", fields);
        body.put("path", request.getRequestURI());

        return ResponseEntity.badRequest().body(body); // 400
    }
}