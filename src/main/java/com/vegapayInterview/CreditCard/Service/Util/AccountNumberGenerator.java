package com.vegapayInterview.CreditCard.Service.Util;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 13:19
 */

public class AccountNumberGenerator {

    private static long accountNumberCount = 1111_1111_1111l;
    public static synchronized String generateAccountNumber(){

        return String.valueOf(accountNumberCount++);
    }
}
