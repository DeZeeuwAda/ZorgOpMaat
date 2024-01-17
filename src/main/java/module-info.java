module com.example.zorgopmaat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.zorgopmaat to javafx.fxml;
    exports com.example.zorgopmaat;
}