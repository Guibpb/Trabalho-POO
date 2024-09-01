module com.example.testebotao {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testebotao to javafx.fxml;
    exports com.example.testebotao;
}