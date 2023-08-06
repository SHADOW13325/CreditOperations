package com.vegapayInterview.CreditCard.Models.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 16:56
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private long customerId;
    private String accountId;
    private String name;
    private String mobile;
    private long accountLimit;
    private long perTransactionLimit;
    private long lastAccountLimit;
    private long lastPerTransactionLimit;
    private LocalDateTime accountLimitUpdateTime;
    private LocalDateTime perTransactionLimitUpdateTime;
    private LocalDateTime accountCreatedOn;
    private HTTPResponse httpResponse;

    public UserResponse() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(long accountLimit) {
        this.accountLimit = accountLimit;
    }

    public long getPerTransactionLimit() {
        return perTransactionLimit;
    }

    public void setPerTransactionLimit(long perTransactionLimit) {
        this.perTransactionLimit = perTransactionLimit;
    }

    public long getLastAccountLimit() {
        return lastAccountLimit;
    }

    public void setLastAccountLimit(long lastAccountLimit) {
        this.lastAccountLimit = lastAccountLimit;
    }

    public long getLastPerTransactionLimit() {
        return lastPerTransactionLimit;
    }

    public void setLastPerTransactionLimit(long lastPerTransactionLimit) {
        this.lastPerTransactionLimit = lastPerTransactionLimit;
    }

    public LocalDateTime getAccountLimitUpdateTime() {
        return accountLimitUpdateTime;
    }

    public void setAccountLimitUpdateTime(LocalDateTime accountLimitUpdateTime) {
        this.accountLimitUpdateTime = accountLimitUpdateTime;
    }

    public LocalDateTime getPerTransactionLimitUpdateTime() {
        return perTransactionLimitUpdateTime;
    }

    public void setPerTransactionLimitUpdateTime(LocalDateTime perTransactionLimitUpdateTime) {
        this.perTransactionLimitUpdateTime = perTransactionLimitUpdateTime;
    }

    public LocalDateTime getAccountCreatedOn() {
        return accountCreatedOn;
    }

    public void setAccountCreatedOn(LocalDateTime accountCreatedOn) {
        this.accountCreatedOn = accountCreatedOn;
    }

    public HTTPResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HTTPResponse httpResponse) {
        this.httpResponse = httpResponse;
    }
}
