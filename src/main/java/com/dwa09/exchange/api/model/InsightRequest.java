package com.dwa09.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class InsightRequest {
    @SerializedName("usd_to_lbp")
    public Boolean usdToLbp;
    @SerializedName("date_to_compare")
    public String datetocompare;
    @SerializedName("dollarstobuy")
    public Integer dollarstobuy;
    @SerializedName("value")
    public Integer value1;


    public InsightRequest(Boolean usdToLbp, String datetocompare, Integer dollarstobuy, Integer value1) {
        this.usdToLbp = usdToLbp;
        this.datetocompare = datetocompare;
        this.dollarstobuy = dollarstobuy;
        this.value1 = value1;
    }
}
