package com.users.errorhandler;

import org.hibernate.exception.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public String handleSqlException(final DataIntegrityViolationException e, final Model model) {
        final String message = ((DataException) e.getCause()).getSQLException().getMessage();
        model.addAttribute("message", message);
        return "error";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(final Exception e, final Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}