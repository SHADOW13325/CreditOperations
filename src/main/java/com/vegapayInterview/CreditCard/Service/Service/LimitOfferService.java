package com.vegapayInterview.CreditCard.Service.Service;

import com.vegapayInterview.CreditCard.Models.RequestDTO.LimitOfferCreationRequest;
import com.vegapayInterview.CreditCard.Models.RequestDTO.LimitOfferUpdationRequest;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.LimitOfferResponse;

import java.time.LocalDateTime;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:15
 */

public interface LimitOfferService {

    public LimitOfferResponse createLimitOffer(LimitOfferCreationRequest limitOfferCreationRequest);

    public LimitOfferResponse updateLimitOfferStatus(LimitOfferUpdationRequest limitOfferUpdationRequest);

    public LimitOfferResponse getActiveLimitOffers(String accountId, String activeDate);
}
