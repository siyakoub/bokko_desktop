package com.msyconseil.bokko_desktop.utils.adapterType;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.msyconseil.bokko_desktop.model.TrajetModel;
import com.msyconseil.bokko_desktop.model.UserModel;

import java.io.IOException;

public class TrajetModelTypeAdapter extends TypeAdapter<TrajetModel> {
    @Override
    public void write(JsonWriter out, TrajetModel trajet) throws IOException {
        out.beginObject();
        out.name("depart").value(trajet.getDepart());
        out.name("arrivee").value(trajet.getArrivee());
        out.name("dateHeureDepart").value(trajet.getDateHeureDepart());
        out.name("nbPlaces").value(trajet.getNbPlaces());
        out.name("prix").value(trajet.getPrix());
        out.name("statut").value(trajet.getStatut());
        out.name("id").value(trajet.getId());

        out.name("userDTO");
        if (trajet.getUserDTO() != null) {
            out.beginObject();
            out.name("id").value(trajet.getUserDTO().getId());
            out.name("name").value(trajet.getUserDTO().getName());
            out.name("passwordIsChange").value(trajet.getUserDTO().isPasswordIsChange());
            out.name("firstName").value(trajet.getUserDTO().getFirstName());
            out.name("email").value(trajet.getUserDTO().getEmail());
            out.name("phoneNumber").value(trajet.getUserDTO().getPhoneNumber());
            out.name("token").value(trajet.getUserDTO().getToken());
            out.name("dateInscription").value(trajet.getUserDTO().getDateInscription());
            out.name("statut").value(trajet.getUserDTO().getStatut());
            out.endObject();
        } else {
            out.nullValue();
        }

        out.endObject();
    }

    @Override
    public TrajetModel read(JsonReader in) throws IOException {
        TrajetModel trajet = new TrajetModel();
        UserModel user = new UserModel();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "depart":
                    trajet.setDepart(in.nextString());
                    break;
                case "arrivee":
                    trajet.setArrivee(in.nextString());
                    break;
                case "dateHeureDepart":
                    trajet.setDateHeureDepart(in.nextString());
                    break;
                case "nbPlaces":
                    trajet.setNbPlaces(in.nextInt());
                    break;
                case "prix":
                    trajet.setPrix(in.nextDouble());
                    break;
                case "statut":
                    trajet.setStatut(in.nextString());
                    break;
                case "id":
                    trajet.setId(in.nextInt());
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
                    trajet.setUserDTO(user);
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        return trajet;
    }
}
