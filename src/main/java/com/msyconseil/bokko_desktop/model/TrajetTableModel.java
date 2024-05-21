package com.msyconseil.bokko_desktop.model;

public class TrajetTableModel {

    private String depart;

    private String arrivee;

    private String dateHeure;

    private int placesDispo;

    private double prixUnitaire;

    private String statut;

    public TrajetTableModel() {

    }

    TrajetTableModel(String depart, String arrivee, String dateHeure, int placesDispo, int prixUnitaire, String statut) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.dateHeure = dateHeure;
        this.placesDispo = placesDispo;
        this.prixUnitaire = prixUnitaire;
        this.statut = statut;
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

    public String getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(String dateHeure) {
        this.dateHeure = dateHeure;
    }

    public int getPlacesDispo() {
        return placesDispo;
    }

    public void setPlacesDispo(int placesDispo) {
        this.placesDispo = placesDispo;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
