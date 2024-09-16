module com.example.dolist {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dolist to javafx.fxml;
    exports com.example.dolist;
}