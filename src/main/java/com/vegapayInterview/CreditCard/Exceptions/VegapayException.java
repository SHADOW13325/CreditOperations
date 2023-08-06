package com.vegapayInterview.CreditCard.Exceptions;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 11:45
 */

public class VegapayException extends RuntimeException{

    public String code;
    public String message;

    public VegapayException(String message, String code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
