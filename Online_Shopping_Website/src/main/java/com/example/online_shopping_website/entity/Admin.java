package com.example.online_shopping_website.entity;


import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
public class Admin extends User implements Serializable {
    private BigDecimal profitAccount;

    private BigDecimal shopAccount;

    public BigDecimal getProfitAccount() {
        return profitAccount;
    }

    public void setProfitAccount(BigDecimal profitAccount) {
        this.profitAccount = profitAccount;
    }

    public BigDecimal getShopAccount() {
        return shopAccount;
    }

    public void setShopAccount(BigDecimal shopAccount) {
        this.shopAccount = shopAccount;
    }

    public Admin(BigDecimal profitAccount) {
        this.profitAccount = profitAccount;
    }

    public Admin(BigDecimal profitAccount, BigDecimal shopAccount) {
        this.profitAccount = profitAccount;
        this.shopAccount = shopAccount;
    }
}
