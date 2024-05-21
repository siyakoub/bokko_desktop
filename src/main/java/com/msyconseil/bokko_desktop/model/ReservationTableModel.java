package com.msyconseil.bokko_desktop.model;

public class ReservationTableModel {

    private String emailClient;

    private String trajet;

    private int placereserv;

    private String dateReserv;

    private String statut;


    public ReservationTableModel() {

    }

    public ReservationTableModel(String emailClient, String trajet, int placereserv, String dateReserv, String statut) {
        this.emailClient = emailClient;
        this.trajet = trajet;
        this.placereserv = placereserv;
        this.dateReserv = dateReserv;
        this.statut = statut;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public String getTrajet() {
        return trajet;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public void setTrajet(String trajet) {
        this.trajet = trajet;
    }

    public void setPlacereserv(int placereserv) {
        this.placereserv = placereserv;
    }

    public void setDateReserv(String dateReserv) {
        this.dateReserv = dateReserv;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getPlacereserv() {
        return placereserv;
    }

    public String getDateReserv() {
        return dateReserv;
    }

    public String getStatut() {
        return statut;
    }
}
