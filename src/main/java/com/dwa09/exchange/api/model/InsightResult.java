package com.dwa09.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class InsightResult {
    @SerializedName("loss")
    public Boolean loss;
    @SerializedName("value")
    public Float value1;

    public Boolean getLoss() {
        return loss;
    }

    public Float getValue1() {
        return value1;
    }
}
