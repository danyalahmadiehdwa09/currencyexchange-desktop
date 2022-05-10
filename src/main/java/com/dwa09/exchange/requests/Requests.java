package com.dwa09.exchange.requests;

import com.dwa09.exchange.Authentication;
import com.dwa09.exchange.api.ExchangeService;
import com.dwa09.exchange.api.model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.net.URL;
import java.util.*;

public class Requests implements Initializable {
    public Label usdfunds;
    public Label lbpfunds;
    public ToggleGroup walletType;
    public ToggleGroup requestType;

    public TextField amountToAdd;
    public TextField usdtext;
    public TextField lbptext;
    public TableColumn id;
    public TableColumn lbpamount;
    public TableColumn usdamount;
    public TableColumn added_date;
    ;
    public TableColumn user_id;
    public TableColumn usd_to_lbp;
    public ArrayList<User> users;
    public Map<String, Integer> userIdNames = new HashMap<String, Integer>();

    private ObservableList<String> options =
            FXCollections.observableArrayList(
            );

    public ComboBox usersList = new ComboBox(options);
    @FXML
    public TableView<Request> requestsTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getRequests();
        getUsers();

    }

    public void commitRequest(ActionEvent actionEvent) {
        Request rq = requestsTable.getSelectionModel().getSelectedItem();
        String userToken = Authentication.getInstance().getToken();
        ExchangeService.exchangeApi().commitTrade(rq,
                "Bearer " + userToken).enqueue(new Callback<Request>() {

            @Override
            public void onResponse(Call<Request> call, Response<Request>
                    response) {
                getRequests();
            }

            @Override
            public void onFailure(Call<Request> call, Throwable throwable) {
            }
        });
    }

    public void removeRequest(ActionEvent actionEvent) {
        Request rq = requestsTable.getSelectionModel().getSelectedItem();
        Integer ids = requestsTable.getSelectionModel().getSelectedItem().getId();
        ExchangeService.exchangeApi().declineRequest(ids).enqueue(new Callback<Integer>() {

            @Override
            public void onResponse(Call<Integer> call, Response<Integer>
                    response) {
                getRequests();

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
            }
        });
    }

    public void addRequest() {
        Request rq = new Request(
                Float.parseFloat(usdtext.getText()),
                Float.parseFloat(lbptext.getText()),
                ((RadioButton)
                        requestType.getSelectedToggle()).getText().equals("Sell USD"),
                userIdNames.get(usersList.getValue())
        );

        ExchangeService.exchangeApi().addRequest(rq, "Bearer " +
                        Authentication.getInstance().getToken())
                .enqueue(new Callback<Request>() {
                    @Override
                    public void onResponse(Call<Request> call,
                                           Response<Request> response) {

                    }

                    @Override
                    public void onFailure(Call<Request> call,
                                          Throwable throwable) {
                    }
                });
    }

    public void getRequests() {
        id.setCellValueFactory(new
                PropertyValueFactory<Request, Long>("id"));
        lbpamount.setCellValueFactory(new
                PropertyValueFactory<Request, Long>("lbp_amount"));
        usdamount.setCellValueFactory(new
                PropertyValueFactory<Request, Long>("usd_amount"));
        added_date.setCellValueFactory(new
                PropertyValueFactory<Transaction, String>("addedDate"));
        usd_to_lbp.setCellValueFactory(new
                PropertyValueFactory<Transaction, String>("usd_to_lbp"));
        user_id.setCellValueFactory(new
                PropertyValueFactory<Transaction, String>("user_id"));
        ExchangeService.exchangeApi().getRequests("Bearer " +
                        Authentication.getInstance().getToken())
                .enqueue(new Callback<List<Request>>() {
                    @Override
                    public void onResponse(Call<List<Request>> call,
                                           Response<List<Request>> response) {
                        requestsTable.getItems().setAll(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Request>> call,
                                          Throwable throwable) {
                    }
                });
    }

    public void getUsers() {
        ExchangeService.exchangeApi().getUsers("Bearer " +
                        Authentication.getInstance().getToken())
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call,
                                           Response<List<User>> response) {
                        users = new ArrayList<>(response.body());
                        for (int i = 0; i < users.size(); i++) {

                            usersList.getItems().add(users.get(i).getUsername());
                            userIdNames.put(users.get(i).getUsername(), users.get(i).getId());
                        }


                    }

                    @Override
                    public void onFailure(Call<List<User>> call,
                                          Throwable throwable) {
                    }
                });


    }
}
