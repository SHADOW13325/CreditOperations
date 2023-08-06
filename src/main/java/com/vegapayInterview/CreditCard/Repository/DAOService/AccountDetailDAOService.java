package com.vegapayInterview.CreditCard.Repository.DAOService;

import com.vegapayInterview.CreditCard.Repository.Entities.AccountDetailDO;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:08
 */

public interface AccountDetailDAOService {

    void saveOrUpdate(AccountDetailDO accountDetailDO);

    public AccountDetailDO getAccount(String accountId);
}
