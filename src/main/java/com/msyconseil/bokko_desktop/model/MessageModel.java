package com.msyconseil.bokko_desktop.model;
import com.google.gson.annotations.SerializedName;

public class MessageModel {

    @SerializedName("expediteur")
    private UserModel expediteur;

    @SerializedName("destinataire")
    private UserModel destinataire;

    @SerializedName("contenu")
    private String contenu;

    @SerializedName("dateHeureEnvoi")
    private String dateHeureEnvoi;

    @SerializedName("lu")
    private boolean lu;

    @SerializedName("id")
    private int id;

    public MessageModel() {

    }

    public MessageModel(UserModel expediteur, UserModel destinataire, String contenu, String dateHeureEnvoi, boolean lu, int id) {
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.contenu = contenu;
        this.dateHeureEnvoi = dateHeureEnvoi;
        this.lu = lu;
        this.id = id;
    }

    public UserModel getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(UserModel expediteur) {
        this.expediteur = expediteur;
    }

    public UserModel getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(UserModel destinataire) {
        this.destinataire = destinataire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDateHeureEnvoi() {
        return dateHeureEnvoi;
    }

    public void setDateHeureEnvoi(String dateHeureEnvoi) {
        this.dateHeureEnvoi = dateHeureEnvoi;
    }

    public boolean isLu() {
        return lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
