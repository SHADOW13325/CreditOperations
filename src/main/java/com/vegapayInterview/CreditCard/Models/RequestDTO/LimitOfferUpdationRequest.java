package com.vegapayInterview.CreditCard.Models.RequestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vegapayInterview.CreditCard.Models.Enums.LimitOfferStatus;
import com.vegapayInterview.CreditCard.Service.Validators.ValidateEnum;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 15:21
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LimitOfferUpdationRequest {

    @Min(value = 1, message = "limit offer id must be greater than 0")
    private long limitOfferId;

    @ValidateEnum(enumClass = LimitOfferStatus.class, allowedValues = {"ACCEPTED", "REJECTED"},
            message = "status must be ACCEPTED or REJECTED", ignoreCase = true)
    private String status;

    public LimitOfferUpdationRequest(long limitOfferId, String status) {
        this.limitOfferId = limitOfferId;
        this.status = status;
    }

    public LimitOfferUpdationRequest() {
    }

    public long getLimitOfferId() {
        return limitOfferId;
    }

    public void setLimitOfferId(long limitOfferId) {
        this.limitOfferId = limitOfferId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
