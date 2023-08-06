package com.vegapayInterview.CreditCard.Repository.DAOService.Impl;

import com.vegapayInterview.CreditCard.Repository.DAOService.LimitOfferDetailDAOService;
import com.vegapayInterview.CreditCard.Repository.DAOService.Repositories.LimitOfferDetailRepository;
import com.vegapayInterview.CreditCard.Repository.Entities.LimitOfferDetailDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:10
 */

@Component
public class LimitOfferDetailDAOServiceImpl implements LimitOfferDetailDAOService {

    private final LimitOfferDetailRepository limitOfferDetailRepository;

    @Autowired
    public LimitOfferDetailDAOServiceImpl(LimitOfferDetailRepository limitOfferDetailRepository) {
        this.limitOfferDetailRepository = limitOfferDetailRepository;
    }

    @Override
    public void saveOrUpdate(LimitOfferDetailDO limitOfferDetailDO) {
        limitOfferDetailRepository.save(limitOfferDetailDO);
    }

    @Override
    public LimitOfferDetailDO getLimitOffer(long limitOfferId) {
        return limitOfferDetailRepository.findFirstById(limitOfferId);
    }

    @Override
    public List<LimitOfferDetailDO> getActiveLimitOffers(String accountId, LocalDateTime activeDate, String status) {
        return limitOfferDetailRepository.findByAccountIdAndStatusAndOfferActivationTimeLessThanEqualAndOfferExpiryTimeGreaterThanEqual(accountId, status, activeDate, activeDate);
    }

}
