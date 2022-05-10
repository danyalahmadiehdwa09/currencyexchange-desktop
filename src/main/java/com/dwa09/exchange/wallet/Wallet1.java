package com.dwa09.exchange.wallet;

import com.dwa09.exchange.Authentication;
import com.dwa09.exchange.api.ExchangeService;
import com.dwa09.exchange.api.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.*;

public class Wallet1 implements Initializable {
    public Label usdfunds;
    public Label lbpfunds;
    public ToggleGroup walletType;

    public TextField amountToAdd;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fetchWallet();

    }

    private void fetchWallet() {
        ExchangeService.exchangeApi().getWallet("Bearer " +
                Authentication.getInstance().getToken()).enqueue(new
                                                                         Callback<Wallet>() {
                                                                             @Override
                                                                             public void onResponse(Call<Wallet> call,
                                                                                                    Response<Wallet> response) {
                                                                                 Wallet wallet = response.body();
                                                                                 Platform.runLater(() -> {
                                                                                     usdfunds.setText(wallet.usd_funds.toString());
                                                                                     lbpfunds.setText(wallet.lbp_funds.toString());
                                                                                 });
                                                                             }

                                                                             @Override
                                                                             public void onFailure(Call<Wallet> call, Throwable
                                                                                     throwable) {
                                                                             }
                                                                         });
    }

    public void addToWallet(ActionEvent actionEvent) {
        WalletTransaction walletTransaction = new WalletTransaction(
                ((RadioButton)
                        walletType.getSelectedToggle()).getText().equals("USD"),
                Float.parseFloat(amountToAdd.getText())

        );
        String userToken = Authentication.getInstance().getToken();

        ExchangeService.exchangeApi().addToWallet(walletTransaction,
                "Bearer " + userToken).enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object>
                    response) {
                fetchWallet();
                Platform.runLater(() -> {
                    amountToAdd.setText("");
                });
            }

            @Override
            public void onFailure(Call<Object> call, Throwable throwable) {
            }
        });
    }
}