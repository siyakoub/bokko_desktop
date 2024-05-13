package com.msyconseil.bokko_desktop.model;
import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("passwordIsChange")
    private boolean passwordIsChange;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("token")
    private String token;

    @SerializedName("dateInscription")
    private String dateInscription;

    @SerializedName("statut")
    private String statut;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPasswordIsChange() {
        return passwordIsChange;
    }

    public void setPasswordIsChange(boolean passwordIsChange) {
        this.passwordIsChange = passwordIsChange;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public UserModel() {

    }

    public UserModel(int id, String name, boolean passwordIsChange, String firstName, String email, String phoneNumber, String token, String dateInscription, String statut) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.passwordIsChange = passwordIsChange;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.token = token;
        this.dateInscription = dateInscription;
        this.statut = statut;
    }




}
