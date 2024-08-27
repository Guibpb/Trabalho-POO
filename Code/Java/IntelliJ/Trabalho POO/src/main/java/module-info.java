module com.spotifyusp.trabalhopoo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.spotifyusp.trabalhopoo to javafx.fxml;
    exports com.spotifyusp.trabalhopoo;
}