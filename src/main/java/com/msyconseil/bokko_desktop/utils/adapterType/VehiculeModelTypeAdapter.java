package com.msyconseil.bokko_desktop.utils.adapterType;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.msyconseil.bokko_desktop.model.UserModel;
import com.msyconseil.bokko_desktop.model.VehiculeModel;

import java.io.IOException;

public class VehiculeModelTypeAdapter extends TypeAdapter<VehiculeModel> {
    @Override
    public void write(JsonWriter out, VehiculeModel vehicule) throws IOException {
        out.beginObject();
        out.name("marque").value(vehicule.getMarque());
        out.name("modele").value(vehicule.getModele());
        out.name("couleur").value(vehicule.getCouleur());
        out.name("immatriculation").value(vehicule.getImmatriculation());
        out.name("annee").value(vehicule.getAnnee());
        out.name("used").value(vehicule.getUsed());
        out.name("id").value(vehicule.getId());

        out.name("userDTO");
        if (vehicule.getUserDTO() != null) {
            out.beginObject();
            out.name("id").value(vehicule.getUserDTO().getId());
            out.name("name").value(vehicule.getUserDTO().getName());
            out.name("passwordIsChange").value(vehicule.getUserDTO().isPasswordIsChange());
            out.name("firstName").value(vehicule.getUserDTO().getFirstName());
            out.name("email").value(vehicule.getUserDTO().getEmail());
            out.name("phoneNumber").value(vehicule.getUserDTO().getPhoneNumber());
            out.name("token").value(vehicule.getUserDTO().getToken());
            out.name("dateInscription").value(vehicule.getUserDTO().getDateInscription());
            out.name("statut").value(vehicule.getUserDTO().getStatut());
            out.endObject();
        } else {
            out.nullValue();
        }

        out.endObject();
    }

    @Override
    public VehiculeModel read(JsonReader in) throws IOException {
        VehiculeModel vehicule = new VehiculeModel();
        UserModel user = new UserModel();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "marque":
                    vehicule.setMarque(in.nextString());
                    break;
                case "modele":
                    vehicule.setModele(in.nextString());
                    break;
                case "couleur":
                    vehicule.setCouleur(in.nextString());
                    break;
                case "immatriculation":
                    vehicule.setImmatriculation(in.nextString());
                    break;
                case "annee":
                    vehicule.setAnnee(in.nextInt());
                    break;
                case "used":
                    vehicule.setUsed(in.nextInt());
                    break;
                case "id":
                    vehicule.setId(in.nextInt());
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
                    vehicule.setUserDTO(user);
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        return vehicule;
    }
}
