module com.example.testeinterfacemusica {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.testeinterfacemusica to javafx.fxml;
    exports com.example.testeinterfacemusica;
}