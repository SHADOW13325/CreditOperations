package com.vegapayInterview.CreditCard.Models.RequestDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author saumitra chauhan
 * @since 05-08-2023 15:08
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountCreationRequest {

    @NotBlank(message = "username can't be empty")
    @Size(min = 3, message = "username length must be greater than or equal to 3")
    private String name;

    @NotBlank(message = "mobile number can't be empty")
    @Size(min = 10, max = 10, message = "mobile number must be of 10 digits")
    private String mobile;

    public AccountCreationRequest(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public AccountCreationRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
