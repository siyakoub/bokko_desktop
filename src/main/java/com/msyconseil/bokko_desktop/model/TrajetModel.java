package com.msyconseil.bokko_desktop.model;
import com.google.gson.annotations.SerializedName;

public class TrajetModel {

    @SerializedName("userDTO")
    private UserModel userDTO;

    @SerializedName("depart")
    private String depart;

    @SerializedName("arrivee")
    private String arrivee;

    @SerializedName("dateHeureDepart")
    private String dateHeureDepart;

    @SerializedName("nbPlaces")
    private int nbPlaces;

    @SerializedName("prix")
    private double prix;

    @SerializedName("statut")
    private String statut;

    @SerializedName("id")
    private int id;

    public TrajetModel() {

    }

    public TrajetModel(UserModel userModel, String depart, String arrivee, String dateHeureDepart, int nbPlaces, double prix, String statut, int id) {
        this.userDTO = userModel;
        this.depart = depart;
        this.arrivee = arrivee;
        this.dateHeureDepart = dateHeureDepart;
        this.nbPlaces = nbPlaces;
        this.prix = prix;
        this.statut = statut;
        this.id = id;
    }

    public UserModel getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserModel userDTO) {
        this.userDTO = userDTO;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public String getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(String dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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
