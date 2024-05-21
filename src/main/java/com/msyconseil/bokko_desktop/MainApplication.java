package com.msyconseil.bokko_desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    static {
        // Vérifiez si le système d'exploitation est macOS et définissez la propriété système
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("prism.forceGPU", "true");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("LoginAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Connexion administrateur");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}