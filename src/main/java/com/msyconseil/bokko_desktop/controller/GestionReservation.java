package com.msyconseil.bokko_desktop.controller;

import com.msyconseil.bokko_desktop.model.ReservationModel;
import com.msyconseil.bokko_desktop.model.ReservationTableModel;
import com.msyconseil.bokko_desktop.model.SessionData;
import com.msyconseil.bokko_desktop.model.UserModel;
import com.msyconseil.bokko_desktop.service.ReservationService;
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

public class GestionReservation implements Initializable {

    private UserModel adminData;

    @FXML
    public TableColumn<ReservationTableModel, String> utilisateur;

    @FXML
    public TableColumn<ReservationTableModel, String> trajet;

    @FXML
    public TableColumn<ReservationTableModel, Integer> placereserv;

    @FXML
    public TableColumn<ReservationTableModel, String> dateHeure;

    @FXML
    public TableColumn<ReservationTableModel, String> statut;

    @FXML
    public TableView<ReservationTableModel> tableReservation;

    @FXML
    public RadioButton inProgressCheck;

    @FXML
    public RadioButton isConfirmedCheck;

    @FXML
    public RadioButton isFinishedCheck;

    private ToggleGroup statusToggleGroup;

    ObservableList<ReservationTableModel> reservationTableModelObservableList = FXCollections.observableArrayList();
    ObservableList<ReservationTableModel> filteredReservationTableModelObservableList = FXCollections.observableArrayList();

    private final UserService userService = new UserService();

    private final ReservationService reservationService = new ReservationService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminData = SessionData.getInstance().getAdminData();

        // Initialize ToggleGroup and add RadioButtons to it
        statusToggleGroup = new ToggleGroup();
        inProgressCheck.setToggleGroup(statusToggleGroup);
        isConfirmedCheck.setToggleGroup(statusToggleGroup);
        isFinishedCheck.setToggleGroup(statusToggleGroup);

        try {
            List<ReservationModel> reservationModelList = reservationService.getAll(adminData.getToken(), "1", "1");
            if (reservationModelList != null) {
                for (ReservationModel reservationModel : reservationModelList) {
                    ReservationTableModel reservationTableModel = setReservationTableModel(reservationModel);
                    reservationTableModelObservableList.add(reservationTableModel);
                }
                utilisateur.setCellValueFactory(new PropertyValueFactory<>("emailClient"));
                trajet.setCellValueFactory(new PropertyValueFactory<>("trajet"));
                placereserv.setCellValueFactory(new PropertyValueFactory<>("placereserv"));
                dateHeure.setCellValueFactory(new PropertyValueFactory<>("dateReserv"));
                statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
                tableReservation.setItems(reservationTableModelObservableList);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            showLoginError();
        }
    }

    private static ReservationTableModel setReservationTableModel(ReservationModel reservationModel) {
        ReservationTableModel reservationTableModel = new ReservationTableModel();
        reservationTableModel.setEmailClient(reservationModel.getUserDTO().getEmail());
        reservationTableModel.setTrajet(reservationModel.getTrajetDTO().getDepart() + " - " + reservationModel.getTrajetDTO().getArrivee());
        reservationTableModel.setPlacereserv(reservationModel.getNbPlacesReserv());
        reservationTableModel.setDateReserv(reservationModel.getDateReservation());
        reservationTableModel.setStatut(reservationModel.getStatut());
        return reservationTableModel;
    }

    public void setAdminData(UserModel adminData) {
        this.adminData = adminData;
    }

    public UserModel getAdminData() {
        return adminData;
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
    private void filterReservations(ActionEvent event) {
        if (inProgressCheck.isSelected()) {
            filteredReservationTableModelObservableList.setAll(reservationTableModelObservableList.stream()
                    .filter(reservation -> "en attente".equals(reservation.getStatut()))
                    .collect(Collectors.toList()));
        } else if (isConfirmedCheck.isSelected()) {
            filteredReservationTableModelObservableList.setAll(reservationTableModelObservableList.stream()
                    .filter(reservation -> "confirmé".equals(reservation.getStatut()))
                    .collect(Collectors.toList()));
        } else if (isFinishedCheck.isSelected()) {
            filteredReservationTableModelObservableList.setAll(reservationTableModelObservableList.stream()
                    .filter(reservation -> "fini".equals(reservation.getStatut()))
                    .collect(Collectors.toList()));
        }
        tableReservation.setItems(filteredReservationTableModelObservableList);
    }

    private void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de connexion");
        alert.setHeaderText("Échec de la connexion");
        alert.setContentText("Un problème est survenu durant la récupération des réservations");
        alert.showAndWait();
    }
}
