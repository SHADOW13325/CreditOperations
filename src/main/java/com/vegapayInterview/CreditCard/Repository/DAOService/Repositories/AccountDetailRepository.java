package com.vegapayInterview.CreditCard.Repository.DAOService.Repositories;

import com.vegapayInterview.CreditCard.Repository.Entities.AccountDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:06
 */

public interface AccountDetailRepository extends JpaRepository<AccountDetailDO, Integer> {

    public AccountDetailDO findFirstByAccountId(String accountNumber);
}
