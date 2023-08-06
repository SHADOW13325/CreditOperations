package com.vegapayInterview.CreditCard.Repository.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 11:50
 */

@Entity
@Table(name = "limit_offer_detail", indexes = {@Index(name = "idx_acc_no", columnList = "account_number")})
public class LimitOfferDetailDO {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "account_number", nullable = false)
    private String accountId;

    @Column(name = "limit_type", nullable = false)
    private String limitType;

    @Column(name = "limit_value", nullable = false)
    private long limitValue;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "offer_activation_time", nullable = false)
    private LocalDateTime offerActivationTime;

    @Column(name = "offer_expiry_time", nullable = false)
    private LocalDateTime offerExpiryTime;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    public LimitOfferDetailDO() {
    }

    public long getId() {
        return id;
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

    public long getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(long limitValue) {
        this.limitValue = limitValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOfferActivationTime() {
        return offerActivationTime;
    }

    public void setOfferActivationTime(LocalDateTime offerActivationTime) {
        this.offerActivationTime = offerActivationTime;
    }

    public LocalDateTime getOfferExpiryTime() {
        return offerExpiryTime;
    }

    public void setOfferExpiryTime(LocalDateTime offerExpiryTime) {
        this.offerExpiryTime = offerExpiryTime;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
