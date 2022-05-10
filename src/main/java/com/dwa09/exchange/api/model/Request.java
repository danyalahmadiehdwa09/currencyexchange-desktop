package com.dwa09.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class Request {
    @SerializedName("usd_amount")
    public Float usd_amount;
    @SerializedName("lbp_amount")
    public Float lbp_amount;
    @SerializedName("usd_to_lbp")
    public Boolean usd_to_lbp;
    @SerializedName("other_user_id")
    public Integer user_id;
    @SerializedName("id")
    public Integer id;
    @SerializedName("added_date")
    public String addedDate;

    public Float getUsd_amount() {
        return usd_amount;
    }

    public Float getLbp_amount() {
        return lbp_amount;
    }

    public Boolean getUsd_to_lbp() {
        return usd_to_lbp;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public Integer getId() {
        return id;
    }

    public Request(Float usd_amount, Float lbp_amount, Boolean usd_to_lbp, Integer user_id) {
        this.usd_amount = usd_amount;
        this.lbp_amount = lbp_amount;
        this.usd_to_lbp = usd_to_lbp;
        this.user_id = user_id;
    }
}