package com.dwa09.exchange.api.model;

import com.google.gson.annotations.SerializedName;
public class WalletTransaction {
    @SerializedName("is_usd")
    public Boolean is_usd;
    @SerializedName("amount_added")
    public Float amount_added;


    public WalletTransaction(Boolean is_usd, Float amount_added)
    {
        this.is_usd = is_usd;
        this.amount_added = amount_added;

    }
}