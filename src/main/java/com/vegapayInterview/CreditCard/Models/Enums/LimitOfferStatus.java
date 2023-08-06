package com.vegapayInterview.CreditCard.Models.Enums;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 11:48
 */

public enum LimitOfferStatus {

    PENDING("PENDING"), ACCEPTED("ACCEPTED"), REJECTED("REJECTED");

    private final String status;

    LimitOfferStatus(String status) {
        this.status=status;
    }

    public String getStatus() {
        return status;
    }

    public static LimitOfferStatus getStatusFromString(String name){
        for (LimitOfferStatus limitOfferStatus : LimitOfferStatus.values()){
            if (name.equalsIgnoreCase(limitOfferStatus.getStatus())){
                return limitOfferStatus;
            }
        }
        return null;
    }
}
