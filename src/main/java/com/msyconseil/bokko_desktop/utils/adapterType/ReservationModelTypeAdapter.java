package com.msyconseil.bokko_desktop.utils.adapterType;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.msyconseil.bokko_desktop.model.ReservationModel;
import com.msyconseil.bokko_desktop.model.TrajetModel;
import com.msyconseil.bokko_desktop.model.UserModel;

import java.io.IOException;

public class ReservationModelTypeAdapter extends TypeAdapter<ReservationModel> {

    @Override
    public void write(JsonWriter out, ReservationModel reservation) throws IOException {
        out.beginObject();
        out.name("nbPlacesReserv").value(reservation.getNbPlacesReserv());
        out.name("dateReservation").value(reservation.getDateReservation());
        out.name("statut").value(reservation.getStatut());
        out.name("id").value(reservation.getId());

        out.name("trajetDTO");
        if (reservation.getTrajetDTO() != null) {
            writeTrajetModel(out, reservation.getTrajetDTO());
        } else {
            out.nullValue();
        }

        out.name("userDTO");
        if (reservation.getUserDTO() != null) {
            writeUserModel(out, reservation.getUserDTO());
        } else {
            out.nullValue();
        }

        out.endObject();
    }

    private void writeTrajetModel(JsonWriter out, TrajetModel trajet) throws IOException {
        out.beginObject();
        out.name("id").value(trajet.getId());
        out.name("depart").value(trajet.getDepart());
        out.name("arrivee").value(trajet.getArrivee());
        out.name("dateHeureDepart").value(trajet.getDateHeureDepart());
        out.name("nbPlaces").value(trajet.getNbPlaces());
        out.name("prix").value(trajet.getPrix());
        out.name("statut").value(trajet.getStatut());

        out.name("userDTO");
        if (trajet.getUserDTO() != null) {
            writeUserModel(out, trajet.getUserDTO());
        } else {
            out.nullValue();
        }
        out.endObject();
    }

    private void writeUserModel(JsonWriter out, UserModel user) throws IOException {
        out.beginObject();
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
    }

    @Override
    public ReservationModel read(JsonReader in) throws IOException {
        ReservationModel reservation = new ReservationModel();
        TrajetModel trajet = new TrajetModel();
        UserModel trajetUser = new UserModel();
        UserModel reservationUser = new UserModel();
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
                                reservationUser.setId(in.nextInt());
                                break;
                            case "name":
                                reservationUser.setName(in.nextString());
                                break;
                            case "passwordIsChange":
                                reservationUser.setPasswordIsChange(in.nextBoolean());
                                break;
                            case "firstName":
                                reservationUser.setFirstName(in.nextString());
                                break;
                            case "email":
                                reservationUser.setEmail(in.nextString());
                                break;
                            case "phoneNumber":
                                reservationUser.setPhoneNumber(in.nextString());
                                break;
                            case "token":
                                reservationUser.setToken(in.nextString());
                                break;
                            case "dateInscription":
                                reservationUser.setDateInscription(in.nextString());
                                break;
                            case "statut":
                                reservationUser.setStatut(in.nextString());
                                break;
                            default:
                                in.skipValue();
                                break;
                        }
                    }
                    in.endObject();
                    reservation.setUserDTO(reservationUser);
                    break;
                default:
                    in.skipValue();
                    break;
            }
        }
        in.endObject();
        return reservation;
    }
}
