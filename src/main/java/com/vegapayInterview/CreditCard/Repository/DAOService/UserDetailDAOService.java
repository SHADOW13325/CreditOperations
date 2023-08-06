package com.vegapayInterview.CreditCard.Repository.DAOService;

import com.vegapayInterview.CreditCard.Repository.Entities.UserDetailDO;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:08
 */

public interface UserDetailDAOService {

    void save(UserDetailDO userDetailDO);

    public UserDetailDO getUserDetails(String accountId);

}
