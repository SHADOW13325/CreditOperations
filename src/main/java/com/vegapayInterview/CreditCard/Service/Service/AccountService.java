package com.vegapayInterview.CreditCard.Service.Service;

import com.vegapayInterview.CreditCard.Models.RequestDTO.AccountCreationRequest;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.UserResponse;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:15
 */

public interface AccountService {

    public UserResponse createAccount(AccountCreationRequest accountCreationRequest);

    public UserResponse getAccountDetails(String accountId);
}
