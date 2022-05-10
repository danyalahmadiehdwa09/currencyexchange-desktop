package com.dwa09.exchange.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsightSets {

    @SerializedName("dates")
    public List<String> dates;
    @SerializedName("usdToLbpAverageByDate")
    public List<Float> usdToLbpAverageByDate;
    @SerializedName("lbpToUsdAveragesByDate")
    public List<Float> lbpToUsdAveragesByDate;

    public List<String> getDates() {
        return dates;
    }

    public List<Float> getUsdToLbpAverageByDate() {
        return usdToLbpAverageByDate;
    }

    public List<Float> getLbpToUsdAveragesByDate() {
        return lbpToUsdAveragesByDate;
    }
}
