package com.vegapayInterview.CreditCard.Models.Enums;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 11:47
 */

public enum LimitType {

    ACCOUNTLIMIT("AccountLimit"), PERTRANSACTIONLIMIT("PerTransactionLimit");

    private final String limitType;

    LimitType(String limitType) {
        this.limitType = limitType;
    }

    public String getLimitType() {
        return limitType;
    }

    public static LimitType getTypeFromString(String name){
        for (LimitType limitType : LimitType.values()){
            if (name.equalsIgnoreCase(limitType.getLimitType())){
                return limitType;
            }
        }
        return null;
    }

}
