package com.vegapayInterview.CreditCard.Models.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vegapayInterview.CreditCard.Repository.Entities.LimitOfferDetailDO;

import java.util.List;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 17:11
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LimitOfferResponse {

    private String accountId;
    private List<LimitOfferDetailDO> limitOffersList;
    private HTTPResponse httpResponse;

    public LimitOfferResponse() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<LimitOfferDetailDO> getLimitOffersList() {
        return limitOffersList;
    }

    public void setLimitOffersList(List<LimitOfferDetailDO> limitOffersList) {
        this.limitOffersList = limitOffersList;
    }

    public HTTPResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(HTTPResponse httpResponse) {
        this.httpResponse = httpResponse;
    }
}
