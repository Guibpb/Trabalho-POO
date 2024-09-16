module com.example.testeimageview {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.testeimageview to javafx.fxml;
    exports com.example.testeimageview;
}