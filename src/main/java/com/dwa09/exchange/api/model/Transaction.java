package com.dwa09.exchange.api.model;

import com.google.gson.annotations.SerializedName;
public class Transaction {
    @SerializedName("usd-amount")
    Float usdAmount;
    @SerializedName("lbp-amount")
    Float lbpAmount;
    @SerializedName("usd-to-lbp")
    Boolean usdToLbp;
    public Transaction(Float usdAmount, Float lbpAmount, Boolean usdToLbp)
    {
        this.usdAmount = usdAmount;
        this.lbpAmount = lbpAmount;
        this.usdToLbp = usdToLbp;
    }
}
