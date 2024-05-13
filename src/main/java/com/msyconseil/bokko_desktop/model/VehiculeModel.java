package com.msyconseil.bokko_desktop.model;
import com.google.gson.annotations.SerializedName;

public class VehiculeModel {

    @SerializedName("userDTO")
    private UserModel userDTO;

    @SerializedName("marque")
    private String marque;

    @SerializedName("modele")
    private String modele;

    @SerializedName("couleur")
    private String couleur;

    @SerializedName("immatriculation")
    private String immatriculation;

    @SerializedName("annee")
    private int annee;

    @SerializedName("used")
    private int used;

    @SerializedName("id")
    private int id;


    public VehiculeModel() {

    }

    public VehiculeModel(UserModel userDTO, String marque, String modele, String couleur, String immatriculation, int annee, int used, int id) {
        this.userDTO = userDTO;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.immatriculation = immatriculation;
        this.annee = annee;
        this.used = used;
        this.id = id;
    }

    public UserModel getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserModel userDTO) {
        this.userDTO = userDTO;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
