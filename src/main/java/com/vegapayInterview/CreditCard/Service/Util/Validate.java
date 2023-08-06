package com.vegapayInterview.CreditCard.Service.Util;

import com.vegapayInterview.CreditCard.Constants.CustomHTTPCodes;
import com.vegapayInterview.CreditCard.Exceptions.VegapayException;
import com.vegapayInterview.CreditCard.Models.Enums.LimitOfferStatus;
import com.vegapayInterview.CreditCard.Models.Enums.LimitType;
import com.vegapayInterview.CreditCard.Models.RequestDTO.LimitOfferCreationRequest;
import com.vegapayInterview.CreditCard.Repository.DAOService.AccountDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.DAOService.LimitOfferDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.Entities.AccountDetailDO;
import com.vegapayInterview.CreditCard.Repository.Entities.LimitOfferDetailDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:18
 */

@Service
public class Validate {

    String pattern = "dd-MM-yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);


    private final AccountDetailDAOService accountDetailDAOService;
    private final LimitOfferDetailDAOService limitOfferDetailDAOService;

    @Autowired
    public Validate(AccountDetailDAOService accountDetailDAOService, LimitOfferDetailDAOService limitOfferDetailDAOService) {
        this.accountDetailDAOService = accountDetailDAOService;
        this.limitOfferDetailDAOService = limitOfferDetailDAOService;
    }

    public AccountDetailDO isAccountExist(String accountId) {
        AccountDetailDO accountDetailDO = accountDetailDAOService.getAccount(accountId);
        if (accountDetailDO == null){
            throw new VegapayException(CustomHTTPCodes.AccountNotFound.getCode(), CustomHTTPCodes.AccountNotFound.getMessage());
        }

        return accountDetailDO;
    }

    public void validateOfferTimings(LimitOfferCreationRequest limitOfferCreationRequest) {

        LocalDateTime offerActivationTime = LocalDate.parse(limitOfferCreationRequest.getOfferActivationTime(),formatter).atStartOfDay();
        LocalDateTime offerExpiryTime = LocalDate.parse(limitOfferCreationRequest.getOfferExpiryTime(),formatter).atStartOfDay();

        if (getDaysDiff(offerActivationTime, offerExpiryTime) < 0) {
            throw new VegapayException(CustomHTTPCodes.OfferDateError.getMessage(), CustomHTTPCodes.OfferDateError.getCode());
        }
        if (getDaysDiff(LocalDateTime.now(),offerActivationTime) < 0) {
            throw new VegapayException(CustomHTTPCodes.PastActivationTime.getMessage(), CustomHTTPCodes.PastActivationTime.getCode());
        }
        if (getDaysDiff(LocalDateTime.now(), offerExpiryTime) < 0) {
            throw new VegapayException(CustomHTTPCodes.PastExpiryTime.getMessage(), CustomHTTPCodes.PastExpiryTime.getCode());
        }
    }

    public static long getDaysDiff(LocalDateTime before, LocalDateTime after) {

        return ChronoUnit.DAYS.between(before, after);
    }


    public void validateNewLimit(AccountDetailDO accountDetailDO, LimitOfferCreationRequest limitOfferCreationRequest) {

        LimitType limitType = LimitType.getTypeFromString(limitOfferCreationRequest.getLimitType());

        if (limitType.getLimitType().equals(LimitType.ACCOUNTLIMIT.getLimitType())){
            if (accountDetailDO.getAccountLimit() >= limitOfferCreationRequest.getNewLimit()){
                throw new VegapayException(CustomHTTPCodes.LimitError.getMessage(), CustomHTTPCodes.LimitError.getCode());
            }

        } else if (limitType.getLimitType().equals(LimitType.PERTRANSACTIONLIMIT.getLimitType())){
            if (accountDetailDO.getPerTransactionLimit() >= limitOfferCreationRequest.getNewLimit()){
                throw new VegapayException(CustomHTTPCodes.LimitError.getMessage(), CustomHTTPCodes.LimitError.getCode());
            }
            if (accountDetailDO.getAccountLimit() < limitOfferCreationRequest.getNewLimit()){
                throw new VegapayException(CustomHTTPCodes.PerTransactionLimitExceeded.getMessage(), CustomHTTPCodes.PerTransactionLimitExceeded.getCode());
            }
        }
    }

    public LimitOfferDetailDO isLimitOfferExist(long limitOfferId) {

        LimitOfferDetailDO limitOfferDetailDO = limitOfferDetailDAOService.getLimitOffer(limitOfferId);
        if (limitOfferDetailDO == null){
            throw new VegapayException(CustomHTTPCodes.LimitOfferNotFound.getMessage(), CustomHTTPCodes.LimitOfferNotFound.getCode());
        }


        return limitOfferDetailDO;
    }

    public void validateOfferExpiry(LimitOfferDetailDO limitOfferDetailDO) {

        LocalDateTime localDateTime = LocalDateTime.now();
        if (getDaysDiff(localDateTime, limitOfferDetailDO.getOfferExpiryTime()) < 0){
            limitOfferDetailDO.setStatus(LimitOfferStatus.REJECTED.getStatus());
            limitOfferDetailDO.setUpdatedOn(LocalDateTime.now());
            limitOfferDetailDAOService.saveOrUpdate(limitOfferDetailDO);
            throw new VegapayException(CustomHTTPCodes.OfferExpired.getMessage(), CustomHTTPCodes.OfferExpired.getCode());
        }
        if (getDaysDiff(limitOfferDetailDO.getOfferActivationTime(), localDateTime) < 0){
            throw new VegapayException(CustomHTTPCodes.OfferActivationWillBeInFuture.getMessage(), CustomHTTPCodes.OfferActivationWillBeInFuture.getCode());
        }
    }

    public void validateLimitOffer(AccountDetailDO accountDetailDO, LimitOfferDetailDO limitOfferDetailDO) {

        VegapayException exception = null;

        if (limitOfferDetailDO.getLimitType().equalsIgnoreCase(LimitType.ACCOUNTLIMIT.getLimitType())){
            if (limitOfferDetailDO.getLimitValue() <= accountDetailDO.getAccountLimit()){
                exception = new VegapayException(CustomHTTPCodes.LimitError.getMessage(), CustomHTTPCodes.LimitError.getCode());
            }
        } else if (limitOfferDetailDO.getLimitType().equalsIgnoreCase(LimitType.PERTRANSACTIONLIMIT.getLimitType())){
            if (limitOfferDetailDO.getLimitValue() <= accountDetailDO.getPerTransactionLimit()){
                exception = new VegapayException(CustomHTTPCodes.LimitError.getMessage(), CustomHTTPCodes.LimitError.getCode());
            }
        }
        if (exception != null){
            limitOfferDetailDO.setStatus(LimitOfferStatus.REJECTED.getStatus());
            limitOfferDetailDO.setUpdatedOn(LocalDateTime.now());
            limitOfferDetailDAOService.saveOrUpdate(limitOfferDetailDO);
            throw exception;
        }
    }
}
