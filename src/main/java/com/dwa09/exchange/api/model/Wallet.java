package com.dwa09.exchange.api.model;

import com.google.gson.annotations.SerializedName;
public class Wallet {
    @SerializedName("usd_funds")
    public Float usd_funds;
    @SerializedName("lbp_funds")
    public Float lbp_funds;
}