package com.vegapayInterview.CreditCard.Repository.DAOService;

import com.vegapayInterview.CreditCard.Repository.Entities.LimitOfferDetailDO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:08
 */

public interface LimitOfferDetailDAOService {

    void saveOrUpdate(LimitOfferDetailDO limitOfferDetailDO);

    LimitOfferDetailDO getLimitOffer(long limitOfferId);

    List<LimitOfferDetailDO> getActiveLimitOffers(String accountId, LocalDateTime activeDate, String status);
}
