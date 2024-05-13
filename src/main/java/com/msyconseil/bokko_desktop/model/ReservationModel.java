package com.msyconseil.bokko_desktop.model;
import com.google.gson.annotations.SerializedName;

public class ReservationModel {

    @SerializedName("trajetDTO")
    private TrajetModel trajetDTO;

    @SerializedName("userDTO")
    private UserModel userDTO;

    @SerializedName("nbPlacesReserv")
    private int nbPlacesReserv;

    @SerializedName("dateReservation")
    private String dateReservation;

    @SerializedName("statut")
    private String statut;

    @SerializedName("id")
    private int id;

    public ReservationModel() {

    }

    public ReservationModel(TrajetModel trajetModel, UserModel userModel, int nbPlacesReserv, String dateReservation, String statut, int id) {
        this.trajetDTO = trajetModel;
        this.userDTO = userModel;
        this.nbPlacesReserv = nbPlacesReserv;
        this.dateReservation = dateReservation;
        this.statut = statut;
        this.id = id;
    }

    public TrajetModel getTrajetDTO() {
        return trajetDTO;
    }

    public void setTrajetDTO(TrajetModel trajetDTO) {
        this.trajetDTO = trajetDTO;
    }

    public UserModel getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserModel userDTO) {
        this.userDTO = userDTO;
    }

    public int getNbPlacesReserv() {
        return nbPlacesReserv;
    }

    public void setNbPlacesReserv(int nbPlacesReserv) {
        this.nbPlacesReserv = nbPlacesReserv;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
