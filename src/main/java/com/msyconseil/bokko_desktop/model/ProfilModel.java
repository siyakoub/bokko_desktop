package com.msyconseil.bokko_desktop.model;

import com.google.gson.annotations.SerializedName;

public class ProfilModel {

    @SerializedName("userDTO")
    private UserModel userDTO;

    @SerializedName("bio")
    private String bio;

    @SerializedName("pictureProfil")
    private String pictureProfil;

    // Getters and Setters

    public ProfilModel() {

    }

    public ProfilModel(UserModel userDTO, String bio, String pictureProfil) {
        this.userDTO = userDTO;
        this.bio = bio;
        this.pictureProfil = pictureProfil;
    }

    public UserModel getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserModel userDTO) {
        this.userDTO = userDTO;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPictureProfil() {
        return pictureProfil;
    }

    public void setPictureProfil(String pictureProfil) {
        this.pictureProfil = pictureProfil;
    }
}
