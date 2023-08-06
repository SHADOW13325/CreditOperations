package com.vegapayInterview.CreditCard.Repository.DAOService.Repositories;

import com.vegapayInterview.CreditCard.Repository.Entities.LimitOfferDetailDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:06
 */

public interface LimitOfferDetailRepository extends JpaRepository<LimitOfferDetailDO, Integer> {

    public LimitOfferDetailDO findFirstById(long limitOfferId);

    public List<LimitOfferDetailDO> findByAccountIdAndStatusAndOfferActivationTimeLessThanEqualAndOfferExpiryTimeGreaterThanEqual(
            String id, String status,LocalDateTime offerActivationTime, LocalDateTime offerExpiryTime
    );
}
