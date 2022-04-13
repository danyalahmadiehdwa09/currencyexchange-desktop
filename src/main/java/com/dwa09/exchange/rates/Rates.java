package com.dwa09.exchange.rates;

import com.dwa09.exchange.Authentication;
import com.dwa09.exchange.api.ExchangeService;
import com.dwa09.exchange.api.model.ExchangeRates;
import com.dwa09.exchange.api.model.Transaction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Rates {
    public Label buyUsdRateLabel;
    public Label sellUsdRateLabel;
    public Label outputAmount;
    public TextField lbpTextField;
    public TextField usdTextField;
    public TextField inputAmount;
    public ToggleGroup transactionType;
    public ToggleGroup conversionType;
    public void initialize() {
        fetchRates();
    }
    private void fetchRates() {
        ExchangeService.exchangeApi().getExchangeRates().enqueue(new
                                                                         Callback<ExchangeRates>() {
                                                                             @Override
                                                                             public void onResponse(Call<ExchangeRates> call,
                                                                                                    Response<ExchangeRates> response) {
                                                                                 ExchangeRates exchangeRates = response.body();
                                                                                 Platform.runLater(() -> {
                                                                                     if ( exchangeRates.lbpToUsd== null)
                                                                                     {buyUsdRateLabel.setText("Not available yet");}
                                                                                     else
                                                                                     {buyUsdRateLabel.setText(exchangeRates.lbpToUsd.toString());}
                                                                                     if ( exchangeRates.usdToLbp== null)
                                                                                     {sellUsdRateLabel.setText("Not available yet");}
                                                                                     else{
                                                                                     sellUsdRateLabel.setText(exchangeRates.usdToLbp.toString());}
                                                                                 });
                                                                             }
                                                                             @Override
                                                                             public void onFailure(Call<ExchangeRates> call, Throwable
                                                                                     throwable) {
                                                                             }
                                                                         });
    }
    public void addTransaction(ActionEvent actionEvent) {
        Transaction transaction = new Transaction(
                Float.parseFloat(usdTextField.getText()),
                Float.parseFloat(lbpTextField.getText()),
                ((RadioButton)
                        transactionType.getSelectedToggle()).getText().equals("Sell USD")
        );
        String userToken = Authentication.getInstance().getToken();
        String authHeader = userToken != null ? "Bearer " + userToken : null;
        ExchangeService.exchangeApi().addTransaction(transaction,
                authHeader).enqueue(new Callback<Object>() {

                                                                                      @Override
                                                                                      public void onResponse(Call<Object> call, Response<Object>
                                                                                              response) {
                                                                                          fetchRates();
                                                                                          Platform.runLater(() -> {
                                                                                              usdTextField.setText("");
                                                                                              lbpTextField.setText("");
                                                                                          });
                                                                                      }
                                                                                      @Override
                                                                                      public void onFailure(Call<Object> call, Throwable throwable)
                                                                                      {
                                                                                      }
                                                                                  });
    }

    public void convert() {
        boolean conv = ((RadioButton) conversionType.getSelectedToggle()).getText().equals("USD to LBP");
        if (conv)
        { if (sellUsdRateLabel.getText().equals( "Not available yet"))
            {outputAmount.setText("No Current Rate");}
            else {
            float z = Float.parseFloat(inputAmount.getText()) * Float.parseFloat(sellUsdRateLabel.getText());
            outputAmount.setText(String.valueOf(z));
            }
    }
        else{
            if (buyUsdRateLabel.getText().equals( "Not available yet"))
            {outputAmount.setText("No Current Rate");}
            else {
                float z = Float.parseFloat(inputAmount.getText()) / Float.parseFloat(buyUsdRateLabel.getText());
                outputAmount.setText(String.valueOf(z));}
        }

        Platform.runLater(() -> {
            inputAmount.setText("");

        });}
}