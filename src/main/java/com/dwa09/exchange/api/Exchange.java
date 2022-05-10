package com.dwa09.exchange.api;

import com.dwa09.exchange.api.model.*;
import com.dwa09.exchange.insight.Insight;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Exchange {
    @POST("/user")
    Call<User> addUser(@Body User user);

    @GET("/users")
    Call<List<User>> getUsers(@Header("Authorization")
                                      String authorization);

    @POST("/authentication")
    Call<Token> authenticate(@Body User user);

    @GET("/exchangeRate")
    Call<ExchangeRates> getExchangeRates();

    @GET("/wallet")
    Call<Wallet> getWallet(@Header("Authorization")
                                   String authorization);

    @PUT("/wallet")
    Call<Object> addToWallet(@Body WalletTransaction walletTrasaction, @Header("Authorization")
            String authorization);

    @GET("/request")
    Call<List<Request>> getRequests(@Header("Authorization")
                                            String authorization);

    @POST("/request")
    Call<Request> addRequest(@Body Request request, @Header("Authorization")
            String authorization);

    @DELETE("/request")
    Call<Integer> declineRequest(@Query("id") Integer id);

    @POST("/trade")
    Call<Request> commitTrade(@Body Request request, @Header("Authorization")
            String authorization);

    @POST("/transaction")
    Call<Object> addTransaction(@Body Transaction transaction,
                                @Header("Authorization") String authorization);

    @GET("/transactions")
    Call<InsightSets> getRates();

    @GET("/transaction")
    Call<List<Transaction>> getTransactions(@Header("Authorization")
                                                    String authorization);

    @POST("/insight")
    Call<InsightResult> insightRequest( @Body InsightRequest insightRequest);
}