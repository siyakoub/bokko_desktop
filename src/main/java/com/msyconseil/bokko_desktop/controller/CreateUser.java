package com.msyconseil.bokko_desktop.controller;

import com.msyconseil.bokko_desktop.model.ProfilModel;
import com.msyconseil.bokko_desktop.model.UserModel;
import com.msyconseil.bokko_desktop.service.ProfilService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CreateUser implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField statutField;

    @FXML
    private TextArea bioField;

    private String base64ProfilePhoto;

    private UserModel adminData;

    private UserModel user;

    protected final ProfilService profilService = new ProfilService();

    @FXML
    private void handleCreateUser() {
        String name = nameField.getText();
        String firstName = firstNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String telephone = telephoneField.getText();
        String statut = statutField.getText();
        String bio = bioField.getText();

        if (name.isEmpty() || firstName.isEmpty() || email.isEmpty() || password.isEmpty() || telephone.isEmpty() || statut.isEmpty() || bio.isEmpty()) {
            showCreateError();
            return;
        }

        Map<String, String> newUser = new HashMap<>();
        newUser.put("name", name);
        newUser.put("firstName", firstName);
        newUser.put("email", email);
        newUser.put("password", password);
        newUser.put("telephone", telephone);
        newUser.put("statut", statut);
        newUser.put("bio", bio);
        newUser.put("photo", this.base64ProfilePhoto);

        try {
            System.out.println(newUser);
            ProfilModel profilModel = profilService.register(newUser);
            if (profilModel != null) {
                showCreateSucces();
                closeWindow();
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            showCreateError();
        }
    }

    @FXML
    private void handleChoosePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo de profil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) nameField.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try (FileInputStream fileInputStream = new FileInputStream(selectedFile)) {
                byte[] bytes = new byte[(int) selectedFile.length()];
                fileInputStream.read(bytes);
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                this.base64ProfilePhoto = "data:image/jpeg;base64," + base64Image;
                System.out.println("Base64 Image: " + this.base64ProfilePhoto);
            } catch (IOException e) {
                e.fillInStackTrace();
                System.out.println(e.getMessage());
                showImageError();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public UserModel getAdminData() {
        return adminData;
    }

    public void setAdminData(UserModel adminData) {
        this.adminData = adminData;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    private void showCreateError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Échec de la connexion");
        alert.setContentText("Un problème est survenu durant la création de l'utilisateur");
        alert.showAndWait();
    }

    private void showImageError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Problème image");
        alert.setHeaderText("Échec de l'ajout de l'image");
        alert.setContentText("Un problème est survenu durant l'ajout de l'image'");
        alert.showAndWait();
    }

    private void showCreateSucces() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès création");
        alert.setHeaderText("Succès");
        alert.setContentText("Succès lors de la création de l'utilisateur !");
        alert.showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

}
