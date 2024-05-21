package com.msyconseil.bokko_desktop.controller;

import com.msyconseil.bokko_desktop.model.SessionData;
import com.msyconseil.bokko_desktop.model.UserModel;
import com.msyconseil.bokko_desktop.service.UserService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginAdmin implements Initializable {

    @FXML
    private AnchorPane myAnchorPane;

    @FXML
    private TextField emailAdmin;

    @FXML
    private PasswordField AdPass;

    private final UserService userService = new UserService();

    @FXML
    private void login(ActionEvent event) {
        try {
            String email = emailAdmin.getText();
            String password = AdPass.getText();
            UserModel userModel = userService.login(email, password);
            if (userModel != null) {
                navigateToDashboard(event, userModel);
            } else {
                showLoginError();
            }
        } catch (Exception e){
            showLoginError();
            e.fillInStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            if (myAnchorPane.getScene() != null) {
                myAnchorPane.getScene().widthProperty().addListener((obs, oldVal, newVal) -> {
                    double width = newVal.doubleValue();
                    myAnchorPane.setPrefWidth(width);
                });

                myAnchorPane.getScene().heightProperty().addListener((obs, oldVal, newVal) -> {
                    double height = newVal.doubleValue();
                    myAnchorPane.setPrefHeight(height);
                });
            }
        });
    }

    private void navigateToDashboard(ActionEvent event, UserModel userModel) {
        try {
            SessionData.getInstance().setAdminData(userModel);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/dashboard.fxml"));
            Parent root = loader.load();
            Dashboard controller = loader.getController();
            controller.setAdminData(userModel); // Transmettre les données d'administration réelles
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin dashboard");
            stage.show();
        } catch (IOException e) {
            e.fillInStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Échec de la connexion");
        alert.setContentText("Veuillez vérifier vos informations de connexion.");
        alert.showAndWait();
    }
}
