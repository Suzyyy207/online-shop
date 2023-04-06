package com.example.online_shopping_website.entity;


import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
public class Admin extends User implements Serializable {
    private BigDecimal profitAccount;

    private BigDecimal intermediaryAccount;

    public BigDecimal getProfitAccount() {
        return profitAccount;
    }

    public BigDecimal getIntermediaryAccount() {
        return intermediaryAccount;
    }

    public void setProfitAccount(BigDecimal profitAccount) {
        this.profitAccount = profitAccount;
    }

    public void setIntermediaryAccount(BigDecimal intermediaryAccount) {
        this.intermediaryAccount = intermediaryAccount;
    }
}
