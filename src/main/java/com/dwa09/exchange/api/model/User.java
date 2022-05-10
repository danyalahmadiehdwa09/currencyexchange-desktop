package com.dwa09.exchange.api.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    Integer id;
    @SerializedName("user_name")
    String username;
    @SerializedName("password")
    String password;
    @SerializedName("lbp_wallet")
    Integer lbp_wallet;
    @SerializedName("usd_wallet")
    Integer usd_wallet;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }
}
