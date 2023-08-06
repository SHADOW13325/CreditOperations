package com.vegapayInterview.CreditCard.Repository.DAOService.Impl;

import com.vegapayInterview.CreditCard.Repository.DAOService.AccountDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.DAOService.Repositories.AccountDetailRepository;
import com.vegapayInterview.CreditCard.Repository.Entities.AccountDetailDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:09
 */

@Component
public class AccountDetailDAOServiceImpl implements AccountDetailDAOService {

    private final AccountDetailRepository accountDetailRepository;

    @Autowired
    public AccountDetailDAOServiceImpl(AccountDetailRepository accountDetailRepository) {
        this.accountDetailRepository = accountDetailRepository;
    }

    @Override
    public void saveOrUpdate(AccountDetailDO accountDetailDO) {
        accountDetailRepository.save(accountDetailDO);
    }

    @Override
    public AccountDetailDO getAccount(String accountId) {
        return accountDetailRepository.findFirstByAccountId(accountId);
    }
}
