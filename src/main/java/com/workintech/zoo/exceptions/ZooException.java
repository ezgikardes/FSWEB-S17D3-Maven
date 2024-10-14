package com.workintech.zoo.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@Data
public class ZooException extends RuntimeException{
    private HttpStatus status;

    public ZooException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
