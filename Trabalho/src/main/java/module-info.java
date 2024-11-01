module com.example.trabalho {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swt;


    opens com.example.trabalho to javafx.fxml;
    exports com.example.trabalho;
}