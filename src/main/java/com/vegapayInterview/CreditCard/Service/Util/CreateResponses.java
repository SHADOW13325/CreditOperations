package com.vegapayInterview.CreditCard.Service.Util;

import com.vegapayInterview.CreditCard.Constants.CustomHTTPCodes;
import com.vegapayInterview.CreditCard.Models.Enums.LimitOfferStatus;
import com.vegapayInterview.CreditCard.Models.RequestDTO.LimitOfferCreationRequest;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.HTTPResponse;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.LimitOfferResponse;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.UserResponse;
import com.vegapayInterview.CreditCard.Repository.Entities.AccountDetailDO;
import com.vegapayInterview.CreditCard.Repository.Entities.LimitOfferDetailDO;
import com.vegapayInterview.CreditCard.Repository.Entities.UserDetailDO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:17
 */

@Service
public class CreateResponses {

    String pattern = "dd-MM-yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);


    public UserResponse createUserResponse(UserDetailDO userDetailDO, AccountDetailDO accountDetailDO) {

        UserResponse userResponse = new UserResponse();
        userResponse.setCustomerId(userDetailDO.getCustomerId());
        userResponse.setAccountId(userDetailDO.getAccountId());
        userResponse.setName(userDetailDO.getName());
        userResponse.setMobile(userDetailDO.getMobile());
        userResponse.setAccountCreatedOn(userDetailDO.getCreatedOn());
        userResponse.setAccountLimit(accountDetailDO.getAccountLimit());
        userResponse.setPerTransactionLimit(accountDetailDO.getPerTransactionLimit());
        userResponse.setLastAccountLimit(accountDetailDO.getLastAccountLimit());
        userResponse.setLastPerTransactionLimit(accountDetailDO.getLastPerTransactionLimit());
        userResponse.setAccountLimitUpdateTime(accountDetailDO.getAccountLimitUpdateTime());
        userResponse.setPerTransactionLimitUpdateTime(accountDetailDO.getPerTransactionLimitUpdateTime());
        userResponse.setHttpResponse(new HTTPResponse(CustomHTTPCodes.AccountCreated.getCode(), CustomHTTPCodes.AccountCreated.getMessage()));
        return userResponse;

    }

    public LimitOfferDetailDO createLimitOfferDetailDO(String accountId, LimitOfferCreationRequest limitOfferCreationRequest) {

        LimitOfferDetailDO limitOfferDetailDO = new LimitOfferDetailDO();
        limitOfferDetailDO.setAccountId(accountId);
        limitOfferDetailDO.setLimitType(limitOfferCreationRequest.getLimitType().toString());
        limitOfferDetailDO.setLimitValue(limitOfferCreationRequest.getNewLimit());
        limitOfferDetailDO.setOfferActivationTime(LocalDate.parse(limitOfferCreationRequest.getOfferActivationTime(),formatter).atStartOfDay());
        limitOfferDetailDO.setOfferExpiryTime(LocalDate.parse(limitOfferCreationRequest.getOfferExpiryTime(),formatter).atStartOfDay());
        limitOfferDetailDO.setStatus(LimitOfferStatus.PENDING.getStatus());
        limitOfferDetailDO.setCreatedOn(LocalDateTime.now());
        limitOfferDetailDO.setUpdatedOn(LocalDateTime.now());
        return limitOfferDetailDO;
    }

    public LimitOfferResponse createLimitOfferResponse(LimitOfferDetailDO limitOfferDetailDO) {

        LimitOfferResponse limitOfferResponse = new LimitOfferResponse();
        limitOfferResponse.setAccountId(limitOfferDetailDO.getAccountId());
        limitOfferResponse.setLimitOffersList(Collections.singletonList(limitOfferDetailDO));
        limitOfferResponse.setHttpResponse(new HTTPResponse(CustomHTTPCodes.LimitOfferCreated.getMessage(), CustomHTTPCodes.LimitOfferCreated.getCode()));
        return limitOfferResponse;
    }

    public LimitOfferResponse createActiveOfferResponses(List<LimitOfferDetailDO> activeLimitOffers, String accountId) {

        LimitOfferResponse limitOfferResponse = new LimitOfferResponse();
        limitOfferResponse.setAccountId(accountId);
        limitOfferResponse.setLimitOffersList(activeLimitOffers);
        limitOfferResponse.setHttpResponse(new HTTPResponse(CustomHTTPCodes.ActiveOffersRetrieved.getMessage(), CustomHTTPCodes.ActiveOffersRetrieved.getCode()));
        return limitOfferResponse;
    }
}
