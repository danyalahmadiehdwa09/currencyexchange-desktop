package com.dwa09.exchange;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class Parent implements Initializable, OnPageCompleteListener{
    public BorderPane borderPane;
    public Button transactionButton;
    public Button loginButton;
    public Button registerButton;
    public Button logoutButton;
    public Button requestButton;
    public Button walletButton;
    public Button insightButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateNavigation();
    }
    @Override
    public void onPageCompleted() {
        swapContent(Section.RATES);
    }
    public void ratesSelected() {
        swapContent(Section.RATES);
    }
    public void transactionsSelected() {
        swapContent(Section.TRANSACTIONS);
    }
    public void loginSelected() {
        swapContent(Section.LOGIN);
    }
    public void registerSelected() {
        swapContent(Section.REGISTER);
    }
    public void insightSelected() {
        swapContent(Section.INSIGHT);
    }
    public void walletSelected() {
        swapContent(Section.WALLET);
    }

    public void requestSelected() {
        swapContent(Section.REQUESTS);
    }
    public void logoutSelected() {
        Authentication.getInstance().deleteToken();
        swapContent(Section.RATES);
    }
    private void swapContent(Section section) {
        try {
            URL url = getClass().getResource(section.getResource());
            FXMLLoader loader = new FXMLLoader(url);
            borderPane.setCenter(loader.load());
            if (section.doesComplete()) {
                ((PageCompleter)
                        loader.getController()).setOnPageCompleteListener(this);
            }
            updateNavigation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum Section {
        RATES,
        TRANSACTIONS,
        LOGIN,
        REQUESTS,
        INSIGHT,
        WALLET,
        REGISTER;

        public boolean doesComplete() {
            return switch (this) {
                case LOGIN, REGISTER -> true;
                default -> false;
            };
        }
        public String getResource() {
            return switch (this) {
                case RATES ->
                        "/com/dwa09/exchange/rates/rates.fxml";
                case TRANSACTIONS ->
                        "/com/dwa09/exchange/transactions/transactions.fxml";
                case LOGIN ->
                        "/com/dwa09/exchange/login/login.fxml";
                case REGISTER ->
                        "/com/dwa09/exchange/register/register.fxml";
                case REQUESTS ->
                        "/com/dwa09/exchange/requests/requests.fxml";
                case INSIGHT ->
                        "/com/dwa09/exchange/insight/insight.fxml";
                case WALLET ->
                        "/com/dwa09/exchange/wallet/wallet.fxml";
                default -> null;
            };
        }

    }
    private void updateNavigation() {
        boolean authenticated = Authentication.getInstance().getToken() !=
                null;
        transactionButton.setManaged(authenticated);
        transactionButton.setVisible(authenticated);
        loginButton.setManaged(!authenticated);
        loginButton.setVisible(!authenticated);
        registerButton.setManaged(!authenticated);
        registerButton.setVisible(!authenticated);
        logoutButton.setManaged(authenticated);
        logoutButton.setVisible(authenticated);
        requestButton.setVisible(authenticated);
        requestButton.setManaged(authenticated);
        insightButton.setVisible(true);
        insightButton.setManaged(true);
        walletButton.setVisible(authenticated);
        walletButton.setManaged(authenticated);
    }

}
