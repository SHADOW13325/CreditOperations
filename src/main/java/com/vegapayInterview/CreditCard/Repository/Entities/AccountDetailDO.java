package com.vegapayInterview.CreditCard.Repository.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 11:50
 */

@Entity
@Table(name = "account_detail", indexes = {@Index(name = "idx_acc_no", columnList = "account_number", unique = true)})
public class AccountDetailDO {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "account_number", nullable = false)
    private String accountId;

    @Column(name = "account_limit", nullable = false)
    private long accountLimit;

    @Column(name = "per_transaction_limit", nullable = false)
    private long perTransactionLimit;

    @Column(name = "last_account_limit")
    private long lastAccountLimit;

    @Column(name = "last_per_transaction_limit")
    private long lastPerTransactionLimit;

    @Column(name = "account_limit_update_time")
    private LocalDateTime accountLimitUpdateTime;

    @Column(name = "per_transaction_limit_update_time")
    private LocalDateTime perTransactionLimitUpdateTime;

    public AccountDetailDO() {
    }

    public AccountDetailDO(String accountId) {
        this.accountId = accountId;
        this.accountLimit = 0;
        this.perTransactionLimit = 0;
        this.lastAccountLimit = 0;
        this.lastPerTransactionLimit = 0;
        this.perTransactionLimitUpdateTime = LocalDateTime.now();
        this.accountLimitUpdateTime = LocalDateTime.now();
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
}
