module com.dwa09.exchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires java.sql;
    requires gson;
    requires retrofit2.converter.gson;
    opens com.dwa09.exchange to javafx.fxml;
    opens com.dwa09.exchange.api.model to gson;
    exports com.dwa09.exchange;
}