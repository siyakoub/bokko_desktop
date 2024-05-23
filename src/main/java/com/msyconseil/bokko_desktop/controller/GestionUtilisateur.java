package com.msyconseil.bokko_desktop.controller;

import com.msyconseil.bokko_desktop.model.SessionData;
import com.msyconseil.bokko_desktop.model.UserModel;
import com.msyconseil.bokko_desktop.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GestionUtilisateur implements Initializable {

    private UserModel adminData;

    private final UserService userService = new UserService();

    @FXML
    private ListView<String> userListView;

    @FXML
    private Button viewProfileButton;

    @FXML
    private Button deactivateProfileButton;

    @FXML
    private Button createAccountButton;

    private ObservableList<String> userObservableList = FXCollections.observableArrayList();
    private List<UserModel> userList;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            adminData = SessionData.getInstance().getAdminData();
            loadUsers();
        } catch (Exception e) {
            e.fillInStackTrace();
            showLoginError();
        }
    }

    private void loadUsers() {
        try {
            userList = userService.getAll(adminData.getToken(), 1, 1);
            if (userList != null) {
                for (UserModel user : userList) {
                    userObservableList.add(user.getFirstName() + " " + user.getName());
                }
                userListView.setItems(userObservableList);
            } else {
                System.out.println("La liste des utilisateurs est nulle.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showLoginError();
        }
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
            e.fillInStackTrace();
            showLogoutError();
        }
    }

    @FXML
    private void viewProfile(ActionEvent event) {
        String selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userList.stream()
                    .filter(u -> (u.getFirstName() + " " + u.getName()).equals(selectedUser))
                    .findFirst().ifPresent(this::openUserProfile);
        }
    }

    private void openUserProfile(UserModel user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/views/profilUser.fxml"));
            Parent root = loader.load();
            ProfilUser controller = loader.getController();
            controller.setAdminData(adminData);
            controller.setUserModel(user);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Profil Utilisateur");
            stage.show();
        } catch (IOException e) {
            e.fillInStackTrace();
            showAfficheError();
        }
    }

    private void openCreateProfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/views/createUser.fxml"));
            Parent root = loader.load();
            CreateUser controller = loader.getController();
            controller.setAdminData(adminData);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Création d'un nouveau profil");
            stage.show();
        } catch (Exception e) {
            e.fillInStackTrace();
            showCreateError();
        }
    }

    private void openDeleteProfilAlerte(UserModel userModel) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de désactivation du compte");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");
        alert.setContentText("Nom : " + userModel.getName() + "\nPrénom : " + userModel.getFirstName());

        ButtonType buttonTypeOui = new ButtonType("Oui");
        ButtonType buttonTypeNon = new ButtonType("Non");

        alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeNon);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOui) {
            try {
                userService.delete(adminData.getToken(), userModel.getEmail());
            } catch (Exception e) {
                e.fillInStackTrace();
                showDeleteError();
            }
        }
    }

    @FXML
    private void deactivateProfile(ActionEvent event) {
        String selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userList.stream()
                    .filter(u -> (u.getFirstName() + " " + u.getName()).equals(selectedUser))
                    .findFirst().ifPresent(this::openDeleteProfilAlerte);
        }
    }

    @FXML
    private void createAccount(ActionEvent event) {
        // Code pour créer un nouveau compte utilisateur
        this.openCreateProfil();
    }

    private void navigateToLoginAdmin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/msyconseil/bokko_desktop/views/loginAdmin.fxml"));
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
            e.printStackTrace();
        }
    }

    private void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Échec de la connexion");
        alert.setContentText("Un problème est survenu durant la récupération des utilisateurs");
        alert.showAndWait();
    }

    private void showLogoutError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Échec de la connexion");
        alert.setContentText("Un problème est survenu durant la déconnexion");
        alert.showAndWait();
    }

    private void showDesacError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Échec de la connexion");
        alert.setContentText("Un problème est survenu durant la désactivation de l'utilisateur");
        alert.showAndWait();
    }

    private void showCreateError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Échec de la connexion");
        alert.setContentText("Un problème est survenu durant la création de l'utilisateur");
        alert.showAndWait();
    }

    private void showAfficheError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Échec de la connexion");
        alert.setContentText("Un problème est survenu durant la visualisation du profil");
        alert.showAndWait();
    }

    private void showDeleteError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de suppression");
        alert.setHeaderText("Échec de la suppression");
        alert.setContentText("Un problème est survenu durant la suppression de l'utilisateur");
        alert.showAndWait();
    }
}
