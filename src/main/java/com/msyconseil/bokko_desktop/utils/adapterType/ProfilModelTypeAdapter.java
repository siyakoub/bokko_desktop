package com.msyconseil.bokko_desktop.utils.adapterType;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.msyconseil.bokko_desktop.model.ProfilModel;
import com.msyconseil.bokko_desktop.model.UserModel;

import java.io.IOException;

public class ProfilModelTypeAdapter extends TypeAdapter<ProfilModel> {
    @Override
    public void write(JsonWriter out, ProfilModel profil) throws IOException {
        out.beginObject();
        out.name("bio").value(profil.getBio());
        out.name("pictureProfil").value(profil.getPictureProfil());

        out.name("userDTO");
        if (profil.getUserDTO() != null) {
            out.beginObject();
            out.name("id").value(profil.getUserDTO().getId());
            out.name("name").value(profil.getUserDTO().getName());
            out.name("passwordIsChange").value(profil.getUserDTO().isPasswordIsChange());
            out.name("firstName").value(profil.getUserDTO().getFirstName());
            out.name("email").value(profil.getUserDTO().getEmail());
            out.name("phoneNumber").value(profil.getUserDTO().getPhoneNumber());
            out.name("token").value(profil.getUserDTO().getToken());
            out.name("dateInscription").value(profil.getUserDTO().getDateInscription());
            out.name("statut").value(profil.getUserDTO().getStatut());
            out.endObject();
        } else {
            out.nullValue();
        }

        out.endObject();
    }

    @Override
    public ProfilModel read(JsonReader in) throws IOException {
        ProfilModel profil = new ProfilModel();
        UserModel user = new UserModel();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "bio":
                    profil.setBio(in.nextString());
                    break;
                case "pictureProfil":
                    profil.setPictureProfil(in.nextString());
                    break;
                case "userDTO":
                    in.beginObject();
                    while (in.hasNext()) {
                        switch (in.nextName()) {
                            case "id":
                                user.setId(in.nextInt());
                                break;
                            case "name":
                                user.setName(in.nextString());
                                break;
                            case "passwordIsChange":
                                user.setPasswordIsChange(in.nextBoolean());
                                break;
                            case "firstName":
                                user.setFirstName(in.nextString());
                                break;
                            case "email":
                                user.setEmail(in.nextString());
                                break;
                            case "phoneNumber":
                                user.setPhoneNumber(in.nextString());
                                break;
                            case "token":
                                user.setToken(in.nextString());
                                break;
                            case "dateInscription":
                                user.setDateInscription(in.nextString());
                                break;
                            case "statut":
                                user.setStatut(in.nextString());
                                break;
                            default:
                                in.skipValue();
                                break;
                        }
                    }
                    in.endObject();
                    profil.setUserDTO(user);
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        return profil;
    }
}
