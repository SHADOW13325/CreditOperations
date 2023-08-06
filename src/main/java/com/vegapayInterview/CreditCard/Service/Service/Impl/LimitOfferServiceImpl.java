package com.vegapayInterview.CreditCard.Service.Service.Impl;

import com.vegapayInterview.CreditCard.Constants.CustomHTTPCodes;
import com.vegapayInterview.CreditCard.Models.Enums.LimitOfferStatus;
import com.vegapayInterview.CreditCard.Models.RequestDTO.LimitOfferCreationRequest;
import com.vegapayInterview.CreditCard.Models.RequestDTO.LimitOfferUpdationRequest;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.HTTPResponse;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.LimitOfferResponse;
import com.vegapayInterview.CreditCard.Repository.DAOService.AccountDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.DAOService.LimitOfferDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.DAOService.UserDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.Entities.AccountDetailDO;
import com.vegapayInterview.CreditCard.Repository.Entities.LimitOfferDetailDO;
import com.vegapayInterview.CreditCard.Service.Service.LimitOfferService;
import com.vegapayInterview.CreditCard.Service.Util.Converter;
import com.vegapayInterview.CreditCard.Service.Util.CreateResponses;
import com.vegapayInterview.CreditCard.Service.Util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:16
 */

@Service
public class LimitOfferServiceImpl implements LimitOfferService {

    String pattern = "dd-MM-yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    private final Converter converter;
    private final UserDetailDAOService userDetailDAOService;
    private final AccountDetailDAOService accountDetailDAOService;
    private final LimitOfferDetailDAOService limitOfferDetailDAOService;
    private final CreateResponses createResponses;
    private final Validate validate;

    @Autowired
    public LimitOfferServiceImpl(Converter converter, UserDetailDAOService userDetailDAOService, AccountDetailDAOService accountDetailDAOService, LimitOfferDetailDAOService limitOfferDetailDAOService, CreateResponses createResponses, Validate validate) {
        this.converter = converter;
        this.userDetailDAOService = userDetailDAOService;
        this.accountDetailDAOService = accountDetailDAOService;
        this.limitOfferDetailDAOService = limitOfferDetailDAOService;
        this.createResponses = createResponses;
        this.validate = validate;
    }

    @Override
    public LimitOfferResponse createLimitOffer(LimitOfferCreationRequest limitOfferCreationRequest) {

        validate.validateOfferTimings(limitOfferCreationRequest);
        AccountDetailDO accountDetailDO = validate.isAccountExist(limitOfferCreationRequest.getAccountId());
        validate.validateNewLimit(accountDetailDO, limitOfferCreationRequest);
        LimitOfferDetailDO limitOfferDetailDO = createResponses.createLimitOfferDetailDO(accountDetailDO.getAccountId(), limitOfferCreationRequest);
        limitOfferDetailDAOService.saveOrUpdate(limitOfferDetailDO);
        return createResponses.createLimitOfferResponse(limitOfferDetailDO);
    }

    @Override
    public LimitOfferResponse updateLimitOfferStatus(LimitOfferUpdationRequest limitOfferUpdationRequest) {

        LimitOfferDetailDO limitOfferDetailDO = validate.isLimitOfferExist(limitOfferUpdationRequest.getLimitOfferId());
        if (!limitOfferDetailDO.getStatus().equalsIgnoreCase(LimitOfferStatus.PENDING.getStatus())){
            LimitOfferResponse limitOfferResponse = createResponses.createLimitOfferResponse(limitOfferDetailDO);
            limitOfferResponse.setHttpResponse(new HTTPResponse(CustomHTTPCodes.LimitOfferExpired.getMessage(), CustomHTTPCodes.LimitOfferExpired.getCode()));
            return limitOfferResponse;
        }
        validate.validateOfferExpiry(limitOfferDetailDO);
        converter.updateLimitOfferStatus(limitOfferDetailDO, LimitOfferStatus.getStatusFromString(limitOfferUpdationRequest.getStatus()));
        limitOfferDetailDAOService.saveOrUpdate(limitOfferDetailDO);
        HTTPResponse httpResponse = new HTTPResponse(CustomHTTPCodes.LimitOfferRejected.getMessage(), CustomHTTPCodes.LimitOfferRejected.getCode());
        if (limitOfferUpdationRequest.getStatus().equalsIgnoreCase(LimitOfferStatus.ACCEPTED.getStatus())){
            AccountDetailDO accountDetailDO = accountDetailDAOService.getAccount(limitOfferDetailDO.getAccountId());
            validate.validateLimitOffer(accountDetailDO, limitOfferDetailDO);
            converter.updateAccountDetailDO(accountDetailDO, limitOfferDetailDO);
            accountDetailDAOService.saveOrUpdate(accountDetailDO);
            httpResponse = new HTTPResponse(CustomHTTPCodes.LimitOfferAccepted.getMessage(), CustomHTTPCodes.LimitOfferAccepted.getCode());
        }
        LimitOfferResponse limitOfferResponse = createResponses.createLimitOfferResponse(limitOfferDetailDO);
        limitOfferResponse.setHttpResponse(httpResponse);
        return limitOfferResponse;
    }

    @Override
    public LimitOfferResponse getActiveLimitOffers(String accountId, String activeDate) {
        LocalDateTime temp;
        if (activeDate == null){
            temp = LocalDate.now().atStartOfDay();
        } else {
            temp = LocalDate.parse(activeDate,formatter).atStartOfDay();
        }
        AccountDetailDO accountDetailDO = validate.isAccountExist(accountId);
        List<LimitOfferDetailDO> activeLimitOffers = limitOfferDetailDAOService.getActiveLimitOffers(accountId, temp, LimitOfferStatus.PENDING.getStatus());
        return createResponses.createActiveOfferResponses(activeLimitOffers, accountDetailDO.getAccountId());
    }
}
