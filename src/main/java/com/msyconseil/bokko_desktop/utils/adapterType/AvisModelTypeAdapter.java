package com.msyconseil.bokko_desktop.utils.adapterType;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.msyconseil.bokko_desktop.model.AvisModel;
import com.msyconseil.bokko_desktop.model.ReservationModel;
import com.msyconseil.bokko_desktop.model.TrajetModel;
import com.msyconseil.bokko_desktop.model.UserModel;

import java.io.IOException;

public class AvisModelTypeAdapter extends TypeAdapter<AvisModel> {
    @Override
    public void write(JsonWriter out, AvisModel avis) throws IOException {
        out.beginObject();
        out.name("note").value(avis.getNote());
        out.name("commentaire").value(avis.getCommentaire());
        out.name("dateHeureAvis").value(avis.getDateHeureAvis());
        out.name("id").value(avis.getId());

        out.name("userDTO");
        if (avis.getUserDTO() != null) {
            out.beginObject();
            UserModel user = avis.getUserDTO();
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

        out.name("reservationDTO");
        if (avis.getReservationDTO() != null) {
            out.beginObject();
            ReservationModel reservation = avis.getReservationDTO();
            out.name("nbPlacesReserv").value(reservation.getNbPlacesReserv());
            out.name("dateReservation").value(reservation.getDateReservation());
            out.name("statut").value(reservation.getStatut());
            out.name("id").value(reservation.getId());

            out.name("trajetDTO");
            if (reservation.getTrajetDTO() != null) {
                out.beginObject();
                TrajetModel trajet = reservation.getTrajetDTO();
                out.name("id").value(trajet.getId());
                out.name("depart").value(trajet.getDepart());
                out.name("arrivee").value(trajet.getArrivee());
                out.name("dateHeureDepart").value(trajet.getDateHeureDepart());
                out.name("nbPlaces").value(trajet.getNbPlaces());
                out.name("prix").value(trajet.getPrix());
                out.name("statut").value(trajet.getStatut());

                out.name("userDTO");
                if (trajet.getUserDTO() != null) {
                    out.beginObject();
                    UserModel user = trajet.getUserDTO();
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
            } else {
                out.nullValue();
            }

            out.name("userDTO");
            if (reservation.getUserDTO() != null) {
                out.beginObject();
                UserModel user = reservation.getUserDTO();
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
        } else {
            out.nullValue();
        }

        out.endObject();
    }

    @Override
    public AvisModel read(JsonReader in) throws IOException {
        AvisModel avis = new AvisModel();
        UserModel user = new UserModel();
        ReservationModel reservation = new ReservationModel();
        TrajetModel trajet = new TrajetModel();
        UserModel trajetUser = new UserModel();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "note":
                    avis.setNote(in.nextInt());
                    break;
                case "commentaire":
                    avis.setCommentaire(in.nextString());
                    break;
                case "dateHeureAvis":
                    avis.setDateHeureAvis(in.nextString());
                    break;
                case "id":
                    avis.setId(in.nextInt());
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
                    avis.setUserDTO(user);
                    break;
                case "reservationDTO":
                    in.beginObject();
                    while (in.hasNext()) {
                        switch (in.nextName()) {
                            case "nbPlacesReserv":
                                reservation.setNbPlacesReserv(in.nextInt());
                                break;
                            case "dateReservation":
                                reservation.setDateReservation(in.nextString());
                                break;
                            case "statut":
                                reservation.setStatut(in.nextString());
                                break;
                            case "id":
                                reservation.setId(in.nextInt());
                                break;
                            case "trajetDTO":
                                in.beginObject();
                                while (in.hasNext()) {
                                    switch (in.nextName()) {
                                        case "id":
                                            trajet.setId(in.nextInt());
                                            break;
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
                                        case "userDTO":
                                            in.beginObject();
                                            while (in.hasNext()) {
                                                switch (in.nextName()) {
                                                    case "id":
                                                        trajetUser.setId(in.nextInt());
                                                        break;
                                                    case "name":
                                                        trajetUser.setName(in.nextString());
                                                        break;
                                                    case "passwordIsChange":
                                                        trajetUser.setPasswordIsChange(in.nextBoolean());
                                                        break;
                                                    case "firstName":
                                                        trajetUser.setFirstName(in.nextString());
                                                        break;
                                                    case "email":
                                                        trajetUser.setEmail(in.nextString());
                                                        break;
                                                    case "phoneNumber":
                                                        trajetUser.setPhoneNumber(in.nextString());
                                                        break;
                                                    case "token":
                                                        trajetUser.setToken(in.nextString());
                                                        break;
                                                    case "dateInscription":
                                                        trajetUser.setDateInscription(in.nextString());
                                                        break;
                                                    case "statut":
                                                        trajetUser.setStatut(in.nextString());
                                                        break;
                                                    default:
                                                        in.skipValue();
                                                        break;
                                                }
                                            }
                                            in.endObject();
                                            trajet.setUserDTO(trajetUser);
                                            break;
                                        default:
                                            in.skipValue();
                                            break;
                                    }
                                }
                                in.endObject();
                                reservation.setTrajetDTO(trajet);
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
                                reservation.setUserDTO(user);
                                break;
                            default:
                                in.skipValue();
                                break;
                        }
                    }
                    in.endObject();
                    avis.setReservationDTO(reservation);
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        return avis;
    }
}

