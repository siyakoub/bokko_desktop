package com.msyconseil.bokko_desktop.model;
import com.google.gson.annotations.SerializedName;

public class AvisModel {

    @SerializedName("userDTO")
    private UserModel userDTO;

    @SerializedName("reservationDTO")
    private ReservationModel reservationDTO;

    @SerializedName("note")
    private int note;

    @SerializedName("commentaire")
    private String commentaire;

    @SerializedName("dateHeureAvis")
    private String dateHeureAvis;

    @SerializedName("id")
    private int id;

    public AvisModel() {

    }

    public AvisModel(UserModel userDTO, ReservationModel reservationDTO, int note, String commentaire, String dateHeureAvis, int id) {
        this.userDTO = userDTO;
        this.reservationDTO = reservationDTO;
        this.note = note;
        this.commentaire = commentaire;
        this.dateHeureAvis = dateHeureAvis;
        this.id = id;
    }

    public UserModel getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserModel userDTO) {
        this.userDTO = userDTO;
    }

    public ReservationModel getReservationDTO() {
        return reservationDTO;
    }

    public void setReservationDTO(ReservationModel reservationDTO) {
        this.reservationDTO = reservationDTO;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDateHeureAvis() {
        return dateHeureAvis;
    }

    public void setDateHeureAvis(String dateHeureAvis) {
        this.dateHeureAvis = dateHeureAvis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
