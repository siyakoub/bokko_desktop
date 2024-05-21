package com.msyconseil.bokko_desktop.utils.adapterType;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.msyconseil.bokko_desktop.model.UserModel;

import java.io.IOException;

public class UserModelTypeAdapter extends TypeAdapter<UserModel> {
    @Override
    public void write(JsonWriter out, UserModel user) throws IOException {
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
    public UserModel read(JsonReader in) throws IOException {
        UserModel user = new UserModel();
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
                    // Manage null token
                    if (in.peek() == JsonToken.NULL) {
                        in.nextNull();
                        user.setToken(null);
                    } else {
                        user.setToken(in.nextString());
                    }
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
        return user;
    }
}
