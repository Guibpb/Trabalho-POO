module com.example.uspfy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.uspfy to javafx.fxml;
    exports com.example.uspfy;
}