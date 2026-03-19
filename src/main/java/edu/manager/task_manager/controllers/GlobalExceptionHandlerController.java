package edu.manager.task_manager.controllers;

import edu.manager.task_manager.entities.responses.ResponseBase;
import edu.manager.task_manager.exceptions.TaskNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ResponseBase<Void>> handleTaskNotFoundException(TaskNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseBase<Void>(
                        false,
                        exception.getMessage(),
                        null
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase<Void>> handleGenericException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseBase<Void>(
                        false,
                        "Ocorreu um erro interno. Tente novamente mais tarde.",
                        null
                )
        );
    }
}
