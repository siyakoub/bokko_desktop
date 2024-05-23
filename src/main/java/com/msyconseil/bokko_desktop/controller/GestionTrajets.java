package com.msyconseil.bokko_desktop.controller;

import com.msyconseil.bokko_desktop.model.SessionData;
import com.msyconseil.bokko_desktop.model.TrajetModel;
import com.msyconseil.bokko_desktop.model.TrajetTableModel;
import com.msyconseil.bokko_desktop.model.UserModel;
import com.msyconseil.bokko_desktop.service.TrajetService;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GestionTrajets implements Initializable {

    private UserModel adminData;

    @FXML
    public TableColumn<TrajetTableModel, String> depart;

    @FXML
    public TableColumn<TrajetTableModel, String> arrivee;

    @FXML
    public TableColumn<TrajetTableModel, String> dateHeure;

    @FXML
    public TableColumn<TrajetTableModel, Integer> places;

    @FXML
    public TableColumn<TrajetTableModel, Integer> prix;

    @FXML
    public TableColumn<TrajetTableModel, String> statut;

    @FXML
    public TableView<TrajetTableModel> tableTrajet;

    @FXML
    public RadioButton inProgressCheck;

    @FXML
    public RadioButton ToBecomeCheck;

    @FXML
    public RadioButton isFinishedCheck;

    private ToggleGroup statusToggleGroup;

    ObservableList<TrajetTableModel> trajetTableModelObservableList = FXCollections.observableArrayList();
    ObservableList<TrajetTableModel> filteredTrajetTableModelObservableList = FXCollections.observableArrayList();

    private final UserService userService = new UserService();

    private final TrajetService trajetService = new TrajetService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminData = SessionData.getInstance().getAdminData();

        // Initialize ToggleGroup and add RadioButtons to it
        statusToggleGroup = new ToggleGroup();
        ToBecomeCheck.setToggleGroup(statusToggleGroup);
        inProgressCheck.setToggleGroup(statusToggleGroup);
        isFinishedCheck.setToggleGroup(statusToggleGroup);

        try {
            List<TrajetModel> trajetModelList = trajetService.getAll(adminData.getToken(), 1, 1);
            if (trajetModelList != null) {
                for (TrajetModel trajetModel : trajetModelList) {
                    TrajetTableModel trajetTableModel = setTrajetTableModel(trajetModel);
                    trajetTableModelObservableList.add(trajetTableModel);
                }
                depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
                arrivee.setCellValueFactory(new PropertyValueFactory<>("arrivee"));
                dateHeure.setCellValueFactory(new PropertyValueFactory<>("dateHeure"));
                places.setCellValueFactory(new PropertyValueFactory<>("placesDispo"));
                prix.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
                statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
                tableTrajet.setItems(trajetTableModelObservableList);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            System.out.println(e.getMessage());
            showLoginError();
        }
    }

    private static TrajetTableModel setTrajetTableModel(TrajetModel trajetModel) {
        TrajetTableModel trajetTableModel = new TrajetTableModel();
        trajetTableModel.setDepart(trajetModel.getDepart());
        trajetTableModel.setArrivee(trajetModel.getArrivee());
        trajetTableModel.setDateHeure(trajetModel.getDateHeureDepart());
        trajetTableModel.setPlacesDispo(trajetModel.getNbPlaces());
        trajetTableModel.setPrixUnitaire(trajetModel.getPrix());
        trajetTableModel.setStatut(trajetModel.getStatut());
        return trajetTableModel;
    }

    public UserModel getAdminData() {
        return adminData;
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
            e.fillInStackTrace();
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
            e.fillInStackTrace();
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
            e.fillInStackTrace();
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

    @FXML
    private void filterTrajets(ActionEvent event) {
        if (ToBecomeCheck.isSelected()) {
            filteredTrajetTableModelObservableList.setAll(trajetTableModelObservableList.stream()
                    .filter(trajet -> "à venir".equals(trajet.getStatut()))
                    .collect(Collectors.toList()));
        } else if (inProgressCheck.isSelected()) {
            filteredTrajetTableModelObservableList.setAll(trajetTableModelObservableList.stream()
                    .filter(trajet -> "en cours".equals(trajet.getStatut()))
                    .collect(Collectors.toList()));
        } else if (isFinishedCheck.isSelected()) {
            filteredTrajetTableModelObservableList.setAll(trajetTableModelObservableList.stream()
                    .filter(trajet -> "terminé".equals(trajet.getStatut()))
                    .collect(Collectors.toList()));
        }
        tableTrajet.setItems(filteredTrajetTableModelObservableList);
    }

    private void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Échec de la connexion");
        alert.setContentText("Un problème est survenu durant la récupération des trajets");
        alert.showAndWait();
    }
}
