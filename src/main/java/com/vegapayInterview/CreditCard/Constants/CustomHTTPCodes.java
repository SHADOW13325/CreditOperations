package com.vegapayInterview.CreditCard.Constants;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 11:44
 */

public enum CustomHTTPCodes {

    AccountCreated("account created successfully", "VEGAPAY-2000"),
    AccountNotFound("account not found", "VEGAPAY-2001"),
    OfferDateError("offer expiry date must be same or after offer activation date", "VEGAPAY-2002"),
    LimitError("new Limit must be greater than the current limit", "VEGAPAY-2003"),
    OfferExpired("offer is already expired and marked rejected", "VEGAPAY-2004"),
    AccountDetailsSent("account details sent successfully", "VEGAPAY-2005"),
    LimitOfferCreated("limit offer created successfully", "VEGAPAY-2006"),
    LimitOfferAccepted("limit offer accepted successfully", "VEGAPAY-2007"),
    LimitOfferNotFound("limit offer not found", "VEGAPAY-2008"),
    OfferActivationWillBeInFuture("offer can't be activated before activation date", "VEGAPAY-2009"),
    ActiveOffersRetrieved("active offers retrieved successfully", "VEGAPAY-2010"),
    LimitOfferRejected("limit offer rejected successfully", "VEGAPAY-2011"),
    PastActivationTime("offer activation time must be today or in future", "VEGAPAY-2012"),
    PastExpiryTime("offer expiry time must be today or in future", "VEGAPAY-2013"),
    PerTransactionLimitExceeded("per transaction limit can't be greater than your current account limit", "VEGAPAY-2014"),
    LimitOfferExpired("limit offer is not in pending state", "VEGAPAY-2015")

    ;

    private final String message;
    private final String code;

    CustomHTTPCodes(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
