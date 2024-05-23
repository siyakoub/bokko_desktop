package com.msyconseil.bokko_desktop.controller;

import com.msyconseil.bokko_desktop.model.ProfilModel;
import com.msyconseil.bokko_desktop.model.SessionData;
import com.msyconseil.bokko_desktop.model.UserModel;
import com.msyconseil.bokko_desktop.service.ProfilService;
import com.msyconseil.bokko_desktop.service.UserService;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;

public class ProfilUser {

    @FXML
    private ImageView profilPicture;
    private UserModel adminData;

    private final UserService userService = new UserService();
    private final ProfilService profilService = new ProfilService();

    @FXML
    private Text nameText;

    @FXML
    private Text firstNameText;

    @FXML
    private Text emailText;

    @FXML
    private Text phoneNumberText;

    @FXML
    private Text dateInscriptionText;

    @FXML
    private Text statutText;

    private UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
        initializeUserDetails(); // Initialisez les détails utilisateur ici
    }

    public void setUserProfil(UserModel user) {
        nameText.setText(user.getName());
        firstNameText.setText(user.getFirstName());
        emailText.setText(user.getEmail());
        phoneNumberText.setText(user.getPhoneNumber());
        dateInscriptionText.setText(user.getDateInscription());
        statutText.setText(user.getStatut());
    }

    private void setImageFromBase64(String base64String) {
        if (base64String != null && !base64String.isEmpty()) {
            if (base64String.startsWith("data:image")) {
                base64String = base64String.substring(base64String.indexOf(",") + 1);
            }

            byte[] imageBytes = Base64.getDecoder().decode(base64String);
            try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes)) {
                Image image = SwingFXUtils.toFXImage(ImageIO.read(bis), null);
                profilPicture.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAdminData(UserModel adminData) {
        this.adminData = adminData;
    }

    public UserModel getAdminData() {
        return adminData;
    }

    @FXML
    private void logout(ActionEvent event) {
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
            e.fillInStackTrace();
        }
    }

    private void navigateToLoginAdmin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/views/loginAdmin.fxml"));
            Parent root = loader.load();
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
            stage.setTitle("Connexion administrateur");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateTrajetGestion(ActionEvent event) {
        try {
            SessionData.getInstance().setAdminData(adminData);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/views/gestionTrajets.fxml"));
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
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateReservationGestion(ActionEvent event) {
        try {
            SessionData.getInstance().setAdminData(adminData);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/views/gestionReservation.fxml"));
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
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToUserGestion(ActionEvent event) {
        try {
            SessionData.getInstance().setAdminData(adminData);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/views/gestionUtilisateurs.fxml"));
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

    private void initializeUserDetails() {
        if (userModel != null) {
            setUserProfil(userModel);
            try {
                ProfilModel profilModel = profilService.getAProfil(adminData.getToken(), userModel.getEmail());
                if (profilModel != null) {
                    setImageFromBase64(profilModel.getPictureProfil());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
