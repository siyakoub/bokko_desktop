package com.msyconseil.bokko_desktop.controller;

import com.msyconseil.bokko_desktop.model.SessionData;
import com.msyconseil.bokko_desktop.model.UserModel;
import com.msyconseil.bokko_desktop.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class TrajetInfo {

    private UserModel adminData;

    private final UserService userService = new UserService();

    public UserModel getAdminData() {
        return adminData;
    }

    static {
        // Vérifiez si le système d'exploitation est macOS et définissez la propriété système
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
    }

    public void setAdminData(UserModel adminData) {
        this.adminData = adminData;
    }

    @FXML
    public void logout(ActionEvent event) {
        try {
            if (adminData != null) {
                String token = adminData.getToken();
                String email = adminData.getEmail();
                boolean isDeconnected = userService.logout(token, email);
                if (isDeconnected) {
                    navigateToLoginAdmin(event);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navigateToLoginAdmin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/loginAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage;

            // Vérifiez si la source de l'événement est un Node ou un MenuItem
            if (event.getSource() instanceof Node) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            } else if (event.getSource() instanceof MenuItem) {
                stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            } else {
                return;
            }

            stage.setScene(scene);
            stage.setTitle("Connexion administrateur");
            stage.show();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    @FXML
    private void navigateTrajetGestion(ActionEvent event) {
        try {
            SessionData.getInstance().setAdminData(adminData);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/gestionTrajets.fxml"));
            Parent root = loader.load();
            GestionTrajets controller = loader.getController();
            controller.setAdminData(adminData);
            Scene scene = new Scene(root);
            Stage stage;
            if (event.getSource() instanceof Node) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            } else if (event.getSource() instanceof MenuItem) {
                stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            } else {
                return;
            }

            stage.setScene(scene);
            stage.setTitle("Gestion des trajets");
            stage.show();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    @FXML
    private void navigateReservationGestion(ActionEvent event) {
        try {
            SessionData.getInstance().setAdminData(adminData);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/gestionReservation.fxml"));
            Parent root = loader.load();
            GestionReservation controller = loader.getController();
            controller.setAdminData(adminData);
            Scene scene = new Scene(root);
            Stage stage;
            if (event.getSource() instanceof Node) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            } else if (event.getSource() instanceof MenuItem) {
                stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            } else {
                return;
            }

            stage.setScene(scene);
            stage.setTitle("Gestion des réservations");
            stage.show();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    @FXML
    private void navigateToUserGestion(ActionEvent event) {
        try {
            SessionData.getInstance().setAdminData(adminData);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/gestionUtilisateurs.fxml"));
            Parent root = loader.load();
            GestionUtilisateur controller = loader.getController();
            controller.setAdminData(adminData);
            Scene scene = new Scene(root);
            Stage stage;
            if (event.getSource() instanceof Node) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            } else if (event.getSource() instanceof MenuItem) {
                stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            } else {
                return;
            }

            stage.setScene(scene);
            stage.setTitle("Gestion des utilisateurs");
            stage.show();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

}
