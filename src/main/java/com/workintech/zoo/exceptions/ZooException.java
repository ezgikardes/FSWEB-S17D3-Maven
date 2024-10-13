package com.workintech.zoo.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ZooException extends RuntimeException{
    private HttpStatus status;

    public ZooException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
