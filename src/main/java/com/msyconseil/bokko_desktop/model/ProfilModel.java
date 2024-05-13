package com.msyconseil.bokko_desktop.model;
import com.google.gson.annotations.SerializedName;

public class ProfilModel {

    @SerializedName("userDTO")
    private UserModel userDTO;

    @SerializedName("bio")
    private String bio;

    @SerializedName("pictureProfil")
    private String pictureProfil;

    @SerializedName("id")
    private int id;

    public ProfilModel() {

    }

    public ProfilModel(UserModel userDTO, String bio, String pictureProfil, int id) {
        this.userDTO = userDTO;
        this.bio = bio;
        this.pictureProfil = pictureProfil;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
