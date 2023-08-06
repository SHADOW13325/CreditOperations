package com.vegapayInterview.CreditCard.Service.Service.Impl;

import com.vegapayInterview.CreditCard.Constants.CustomHTTPCodes;
import com.vegapayInterview.CreditCard.Models.RequestDTO.AccountCreationRequest;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.HTTPResponse;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.UserResponse;
import com.vegapayInterview.CreditCard.Repository.DAOService.AccountDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.DAOService.UserDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.Entities.AccountDetailDO;
import com.vegapayInterview.CreditCard.Repository.Entities.UserDetailDO;
import com.vegapayInterview.CreditCard.Service.Service.AccountService;
import com.vegapayInterview.CreditCard.Service.Util.AccountNumberGenerator;
import com.vegapayInterview.CreditCard.Service.Util.Converter;
import com.vegapayInterview.CreditCard.Service.Util.CreateResponses;
import com.vegapayInterview.CreditCard.Service.Util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:16
 */

@Service
public class AccountServiceImpl implements AccountService {

    private final Converter converter;
    private final UserDetailDAOService userDetailDAOService;
    private final AccountDetailDAOService accountDetailDAOService;
    private final CreateResponses createResponses;
    private final Validate validate;

    @Autowired
    public AccountServiceImpl(Converter converter, UserDetailDAOService userDetailDAOService, AccountDetailDAOService accountDetailDAOService, CreateResponses createResponses, Validate validate) {
        this.converter = converter;
        this.userDetailDAOService = userDetailDAOService;
        this.accountDetailDAOService = accountDetailDAOService;
        this.createResponses = createResponses;
        this.validate = validate;
    }


    @Override
    public UserResponse createAccount(AccountCreationRequest accountCreationRequest) {

        String accountId = AccountNumberGenerator.generateAccountNumber();
        UserDetailDO userDetailDO = converter.convertAccountCreationRequestToUserDetailDO(accountCreationRequest, accountId);
        AccountDetailDO accountDetailDO = new AccountDetailDO(accountId);
        userDetailDAOService.save(userDetailDO);
        accountDetailDAOService.saveOrUpdate(accountDetailDO);
        UserResponse userResponse = createResponses.createUserResponse(userDetailDO, accountDetailDO);
        return userResponse;
    }

    @Override
    public UserResponse getAccountDetails(String accountId) {

        AccountDetailDO accountDetailDO = validate.isAccountExist(accountId);
        UserDetailDO userDetailDO = userDetailDAOService.getUserDetails(accountId);
        UserResponse userResponse = createResponses.createUserResponse(userDetailDO, accountDetailDO);
        userResponse.setHttpResponse(new HTTPResponse(CustomHTTPCodes.AccountDetailsSent.getCode(), CustomHTTPCodes.AccountDetailsSent.getMessage()));
        return userResponse;
    }
}
