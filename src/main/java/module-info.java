module com.msyconseil.bokko_desktop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.google.gson;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.net.http;
    requires javafx.swing;

    opens com.msyconseil.bokko_desktop to javafx.fxml;
    opens com.msyconseil.bokko_desktop.controller to javafx.fxml;
    opens com.msyconseil.bokko_desktop.model to javafx.base, com.google.gson;
    opens com.msyconseil.bokko_desktop.utils.adapterType to com.google.gson;

    exports com.msyconseil.bokko_desktop;
}
