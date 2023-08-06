package com.vegapayInterview.CreditCard.Repository.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 11:50
 */

@Entity
@Table(name = "user_detail", indexes = {@Index(name = "idx_acc_no", columnList = "account_number", unique = true)})
public class UserDetailDO {

    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_no")
    private String mobile;

    @Column(name = "account_number", nullable = false)
    private String accountId;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    public UserDetailDO() {
    }

    public long getCustomerId() {
        return customerId;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
