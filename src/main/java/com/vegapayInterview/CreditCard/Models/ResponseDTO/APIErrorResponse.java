package com.vegapayInterview.CreditCard.Models.ResponseDTO;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 16:56
 */

public class APIErrorResponse {

    public String statusCode;
    public String statusMessage;

    public APIErrorResponse(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
