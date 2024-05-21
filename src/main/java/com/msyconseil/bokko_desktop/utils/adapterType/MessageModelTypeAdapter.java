package com.msyconseil.bokko_desktop.utils.adapterType;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.msyconseil.bokko_desktop.model.MessageModel;
import com.msyconseil.bokko_desktop.model.UserModel;

import java.io.IOException;

public class MessageModelTypeAdapter extends TypeAdapter<MessageModel> {
    @Override
    public void write(JsonWriter out, MessageModel message) throws IOException {
        out.beginObject();
        out.name("contenu").value(message.getContenu());
        out.name("dateHeureEnvoi").value(message.getDateHeureEnvoi());
        out.name("lu").value(message.isLu());
        out.name("id").value(message.getId());

        out.name("expediteur");
        if (message.getExpediteur() != null) {
            out.beginObject();
            UserModel user = message.getExpediteur();
            out.name("id").value(user.getId());
            out.name("name").value(user.getName());
            out.name("passwordIsChange").value(user.isPasswordIsChange());
            out.name("firstName").value(user.getFirstName());
            out.name("email").value(user.getEmail());
            out.name("phoneNumber").value(user.getPhoneNumber());
            out.name("token").value(user.getToken());
            out.name("dateInscription").value(user.getDateInscription());
            out.name("statut").value(user.getStatut());
            out.endObject();
        } else {
            out.nullValue();
        }

        out.name("destinataire");
        if (message.getDestinataire() != null) {
            out.beginObject();
            UserModel user = message.getDestinataire();
            out.name("id").value(user.getId());
            out.name("name").value(user.getName());
            out.name("passwordIsChange").value(user.isPasswordIsChange());
            out.name("firstName").value(user.getFirstName());
            out.name("email").value(user.getEmail());
            out.name("phoneNumber").value(user.getPhoneNumber());
            out.name("token").value(user.getToken());
            out.name("dateInscription").value(user.getDateInscription());
            out.name("statut").value(user.getStatut());
            out.endObject();
        } else {
            out.nullValue();
        }

        out.endObject();
    }

    @Override
    public MessageModel read(JsonReader in) throws IOException {
        MessageModel message = new MessageModel();
        UserModel expediteur = new UserModel();
        UserModel destinataire = new UserModel();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "contenu":
                    message.setContenu(in.nextString());
                    break;
                case "dateHeureEnvoi":
                    message.setDateHeureEnvoi(in.nextString());
                    break;
                case "lu":
                    message.setLu(in.nextBoolean());
                    break;
                case "id":
                    message.setId(in.nextInt());
                    break;
                case "expediteur":
                    in.beginObject();
                    while (in.hasNext()) {
                        switch (in.nextName()) {
                            case "id":
                                expediteur.setId(in.nextInt());
                                break;
                            case "name":
                                expediteur.setName(in.nextString());
                                break;
                            case "passwordIsChange":
                                expediteur.setPasswordIsChange(in.nextBoolean());
                                break;
                            case "firstName":
                                expediteur.setFirstName(in.nextString());
                                break;
                            case "email":
                                expediteur.setEmail(in.nextString());
                                break;
                            case "phoneNumber":
                                expediteur.setPhoneNumber(in.nextString());
                                break;
                            case "token":
                                expediteur.setToken(in.nextString());
                                break;
                            case "dateInscription":
                                expediteur.setDateInscription(in.nextString());
                                break;
                            case "statut":
                                expediteur.setStatut(in.nextString());
                                break;
                            default:
                                in.skipValue();
                                break;
                        }
                    }
                    in.endObject();
                    message.setExpediteur(expediteur);
                    break;
                case "destinataire":
                    in.beginObject();
                    while (in.hasNext()) {
                        switch (in.nextName()) {
                            case "id":
                                destinataire.setId(in.nextInt());
                                break;
                            case "name":
                                destinataire.setName(in.nextString());
                                break;
                            case "passwordIsChange":
                                destinataire.setPasswordIsChange(in.nextBoolean());
                                break;
                            case "firstName":
                                destinataire.setFirstName(in.nextString());
                                break;
                            case "email":
                                destinataire.setEmail(in.nextString());
                                break;
                            case "phoneNumber":
                                destinataire.setPhoneNumber(in.nextString());
                                break;
                            case "token":
                                destinataire.setToken(in.nextString());
                                break;
                            case "dateInscription":
                                destinataire.setDateInscription(in.nextString());
                                break;
                            case "statut":
                                destinataire.setStatut(in.nextString());
                                break;
                            default:
                                in.skipValue();
                                break;
                        }
                    }
                    in.endObject();
                    message.setDestinataire(destinataire);
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        return message;
    }
}

