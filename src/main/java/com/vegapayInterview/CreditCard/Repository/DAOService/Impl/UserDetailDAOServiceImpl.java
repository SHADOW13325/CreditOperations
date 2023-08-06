package com.vegapayInterview.CreditCard.Repository.DAOService.Impl;

import com.vegapayInterview.CreditCard.Repository.DAOService.Repositories.UserDetailRepository;
import com.vegapayInterview.CreditCard.Repository.DAOService.UserDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.Entities.UserDetailDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:09
 */

@Component
public class UserDetailDAOServiceImpl implements UserDetailDAOService {

    private final UserDetailRepository userDetailRepository;

    @Autowired
    public UserDetailDAOServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public void save(UserDetailDO userDetailDO){
        userDetailRepository.save(userDetailDO);
    }

    @Override
    public UserDetailDO getUserDetails(String accountId) {
        return userDetailRepository.findFirstByAccountId(accountId);
    }
}
