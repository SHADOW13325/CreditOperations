package com.vegapayInterview.CreditCard.Service.Util;

import com.vegapayInterview.CreditCard.Models.Enums.LimitOfferStatus;
import com.vegapayInterview.CreditCard.Models.Enums.LimitType;
import com.vegapayInterview.CreditCard.Models.RequestDTO.AccountCreationRequest;
import com.vegapayInterview.CreditCard.Repository.Entities.AccountDetailDO;
import com.vegapayInterview.CreditCard.Repository.Entities.LimitOfferDetailDO;
import com.vegapayInterview.CreditCard.Repository.Entities.UserDetailDO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:17
 */

@Service
public class Converter {

    public UserDetailDO convertAccountCreationRequestToUserDetailDO(AccountCreationRequest accountCreationRequest, String accountId) {

        UserDetailDO userDetailDO = new UserDetailDO();
        userDetailDO.setAccountId(accountId);
        userDetailDO.setName(accountCreationRequest.getName());
        userDetailDO.setMobile(accountCreationRequest.getMobile());
        userDetailDO.setCreatedOn(LocalDateTime.now());
        return userDetailDO;
    }

    public void updateLimitOfferStatus(LimitOfferDetailDO limitOfferDetailDO, LimitOfferStatus status) {

        limitOfferDetailDO.setUpdatedOn(LocalDateTime.now());
        limitOfferDetailDO.setStatus(status.getStatus());
    }

    public void updateAccountDetailDO(AccountDetailDO accountDetailDO, LimitOfferDetailDO limitOfferDetailDO) {

        if (limitOfferDetailDO.getLimitType().equalsIgnoreCase(LimitType.ACCOUNTLIMIT.getLimitType())){
            accountDetailDO.setLastAccountLimit(accountDetailDO.getAccountLimit());
            accountDetailDO.setAccountLimit(limitOfferDetailDO.getLimitValue());
            accountDetailDO.setAccountLimitUpdateTime(LocalDateTime.now());
        } else if (limitOfferDetailDO.getLimitType().equalsIgnoreCase(LimitType.PERTRANSACTIONLIMIT.getLimitType())){
            accountDetailDO.setLastPerTransactionLimit(accountDetailDO.getPerTransactionLimit());
            accountDetailDO.setPerTransactionLimit(limitOfferDetailDO.getLimitValue());
            accountDetailDO.setPerTransactionLimitUpdateTime(LocalDateTime.now());
        }
    }
}
