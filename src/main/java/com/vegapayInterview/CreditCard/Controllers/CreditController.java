package com.vegapayInterview.CreditCard.Controllers;

import com.vegapayInterview.CreditCard.Constants.ServiceURIs;
import com.vegapayInterview.CreditCard.Models.RequestDTO.AccountCreationRequest;
import com.vegapayInterview.CreditCard.Models.RequestDTO.LimitOfferCreationRequest;
import com.vegapayInterview.CreditCard.Models.RequestDTO.LimitOfferUpdationRequest;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.LimitOfferResponse;
import com.vegapayInterview.CreditCard.Models.ResponseDTO.UserResponse;
import com.vegapayInterview.CreditCard.Service.Service.AccountService;
import com.vegapayInterview.CreditCard.Service.Service.LimitOfferService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 11:45
 */

@RestController
@RequestMapping(value = ServiceURIs.ACCOUNT_API_BASE_PATH, consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditController {

    private final AccountService accountService;
    private final LimitOfferService limitOfferService;

    @Autowired
    public CreditController(AccountService accountService, LimitOfferService limitOfferService) {
        this.accountService = accountService;
        this.limitOfferService = limitOfferService;
    }

    @PostMapping(ServiceURIs.CREATION_API_V1)
    public ResponseEntity<UserResponse> accountCreation(@Valid @RequestBody AccountCreationRequest accountCreationRequest) {

        UserResponse userResponse = accountService.createAccount(accountCreationRequest);
        return ResponseEntity.ok(userResponse);

    }

    @PostMapping(ServiceURIs.CREATE_LIMIT_OFFER_API_V1)
    public ResponseEntity<LimitOfferResponse> createLimitOffer(@Valid @RequestBody LimitOfferCreationRequest limitOfferCreationRequest) {

        LimitOfferResponse limitOfferResponse = limitOfferService.createLimitOffer(limitOfferCreationRequest);
        return ResponseEntity.ok(limitOfferResponse);

    }

    @PostMapping(ServiceURIs.UPDATE_LIMIT_OFFER_API_V1)
    public ResponseEntity<LimitOfferResponse> updateLimitOffer(@Valid @RequestBody LimitOfferUpdationRequest limitOfferUpdationRequest) {

        LimitOfferResponse limitOfferResponse = limitOfferService.updateLimitOfferStatus(limitOfferUpdationRequest);
        return ResponseEntity.ok(limitOfferResponse);

    }

    @GetMapping(ServiceURIs.GET_ACTIVE_LIMIT_OFFERS_API_V1)
    public ResponseEntity<LimitOfferResponse> getLimitOffers(
            @NotBlank(message = "account number can't be empty") @RequestParam("accountId") String accountId,
            @RequestParam(value = "activeDate", required = false) String activeDate) {

        LimitOfferResponse limitOfferResponse = limitOfferService.getActiveLimitOffers(accountId, activeDate);
        return ResponseEntity.ok(limitOfferResponse);

    }

    @GetMapping(ServiceURIs.GET_ACCOUNT_DETAILS_API_V1)
    public ResponseEntity<UserResponse> getAccountDetails(
            @NotBlank(message = "account number can't be empty") @RequestParam("accountId") String accountId) {

        UserResponse userResponse = accountService.getAccountDetails(accountId);
        return ResponseEntity.ok(userResponse);

    }


}
