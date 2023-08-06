package com.vegapayInterview.CreditCard.Models.RequestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vegapayInterview.CreditCard.Models.Enums.LimitType;
import com.vegapayInterview.CreditCard.Service.Validators.ValidateEnum;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 15:10
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LimitOfferCreationRequest {

    @NotBlank(message = "account number can't be empty")
    private String accountId;

    @ValidateEnum(enumClass = LimitType.class, message = "Limit Types must be AccountLimit or PerTransactionLimit", ignoreCase = true)
    private String limitType;

    @Min(value = 1, message = "new limit must be greater than 0")
    private long newLimit;

    @NotBlank(message = "offer Activation date can't be empty")
    private String offerActivationTime;

    @NotBlank(message = "offer expiry date can't be empty")
    private String offerExpiryTime;

    public LimitOfferCreationRequest(String accountId, String limitType, long newLimit, String offerActivationTime, String offerExpiryTime) {
        this.accountId = accountId;
        this.limitType = limitType;
        this.newLimit = newLimit;
        this.offerActivationTime = offerActivationTime;
        this.offerExpiryTime = offerExpiryTime;
    }

    public LimitOfferCreationRequest() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }

    public long getNewLimit() {
        return newLimit;
    }

    public void setNewLimit(long newLimit) {
        this.newLimit = newLimit;
    }

    public String getOfferActivationTime() {
        return offerActivationTime;
    }

    public void setOfferActivationTime(String offerActivationTime) {
        this.offerActivationTime = offerActivationTime;
    }

    public String getOfferExpiryTime() {
        return offerExpiryTime;
    }

    public void setOfferExpiryTime(String offerExpiryTime) {
        this.offerExpiryTime = offerExpiryTime;
    }
}
