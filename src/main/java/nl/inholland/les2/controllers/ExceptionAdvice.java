package nl.inholland.les2.controllers;

import nl.inholland.les2.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionAdvice {

        @ExceptionHandler(value = {Exception.class})
        public ResponseEntity<ErrorMessage> genericException(Exception ex, WebRequest request) {
            ErrorMessage message = new ErrorMessage(
                    ex.getMessage());

            return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
        }

}
