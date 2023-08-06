package com.vegapayInterview.CreditCard.Repository.DAOService.Repositories;

import com.vegapayInterview.CreditCard.Repository.Entities.UserDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:05
 */

public interface UserDetailRepository extends JpaRepository<UserDetailDO, Integer> {

    public UserDetailDO findFirstByAccountId(String accountNumber);
}
