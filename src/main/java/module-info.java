module com.dwa09.exchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;
    requires java.prefs;
    opens com.dwa09.exchange to javafx.fxml;
    opens com.dwa09.exchange.api.model to  javafx.base, gson;
    opens com.dwa09.exchange.login to javafx.fxml;
    opens com.dwa09.exchange.register to javafx.fxml;
    opens com.dwa09.exchange.transactions to javafx.fxml;
    exports com.dwa09.exchange;
    exports com.dwa09.exchange.rates;
    opens com.dwa09.exchange.rates to javafx.fxml;
}
