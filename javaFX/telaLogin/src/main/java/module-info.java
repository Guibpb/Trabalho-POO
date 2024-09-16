module com.example.telalogin {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.telalogin to javafx.fxml;
    exports com.example.telalogin;
}