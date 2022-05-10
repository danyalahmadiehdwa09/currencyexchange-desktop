package com.dwa09.exchange.insight;

import com.dwa09.exchange.Authentication;
import com.dwa09.exchange.api.ExchangeService;
import com.dwa09.exchange.api.model.InsightRequest;
import com.dwa09.exchange.api.model.InsightResult;
import com.dwa09.exchange.api.model.InsightSets;
import com.dwa09.exchange.api.model.Request;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Insight implements Initializable {

    @FXML
    public LineChart<String, Number> chart;

    public XYChart.Series<String, Number> series1 = new XYChart.Series();
    public XYChart.Series<String, Number> series2 = new XYChart.Series();
    public Label result;
    public DatePicker datep;
    public TextField value1;
    public Label result2;
    public DatePicker datep2;
    public TextField value2;
    public ToggleGroup insightType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        series1.setName("USD to LBP Exchange Rates over time");
        series2.setName("LBP to USD Exchange Rates over time");
        getRates();
        chart.getData().add(series1);
        chart.getData().add(series2);


    }


    public void getRates() {
        ExchangeService.exchangeApi().getRates()
                .enqueue(new Callback<InsightSets>() {
                    @Override
                    public void onResponse(Call<InsightSets> call,
                                           Response<InsightSets> response) {
                        for (int i = 0; i < response.body().getDates().size(); i++) {
                            String date = response.body().getDates().get(i);
                            Float n = response.body().getUsdToLbpAverageByDate().get(i);
                            Float n2 = response.body().getLbpToUsdAveragesByDate().get(i);
                            Platform.runLater(() -> {
                                series1.getData().add(new XYChart.Data<>(date, n));
                                series2.getData().add(new XYChart.Data<>(date, n2));

                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<InsightSets> call,
                                          Throwable throwable) {
                    }
                });
    }

    public void compareSell ()
    {
        InsightRequest ir = new InsightRequest(
                true,
                datep.getValue().toString(),
                0,
                Integer.parseInt(value1.getText())

        );

        ExchangeService.exchangeApi().insightRequest(ir)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<InsightResult> call,
                                           Response<InsightResult> response) {
                        Platform.runLater(() -> {
                            if (response.body().loss) {
                                result.setText("You would have lost " + response.body().value1.toString() + "LBP");
                            } else {
                                result.setText("You would have gained " + response.body().value1.toString() + "LBP");
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<InsightResult> call,
                                          Throwable throwable) {
                    }
                });}

    public void compareBuy() {
        InsightRequest ir;
        if (((RadioButton)
                insightType.getSelectedToggle()).getText().equals("LBP Spent"))
        {
         ir = new InsightRequest(
                false,
                datep2.getValue().toString(),
                0,
                 Integer.parseInt(value2.getText())

        ); }
        else
        {ir = new InsightRequest(
                false,
                datep2.getValue().toString(),
                Integer.parseInt(value2.getText()),
                0

        );}

        ExchangeService.exchangeApi().insightRequest(ir)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<InsightResult> call,
                                           Response<InsightResult> response) {
                        Platform.runLater(() -> {
                            if (response.body().loss) {
                                result2.setText("You would have lost " + response.body().value1.toString());
                            } else {
                                result2.setText("You would have gained " + response.body().value1.toString());
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<InsightResult> call,
                                          Throwable throwable) {
                    }
                });}
}
