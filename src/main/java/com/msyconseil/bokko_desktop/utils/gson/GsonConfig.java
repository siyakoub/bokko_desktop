package com.msyconseil.bokko_desktop.utils.gson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msyconseil.bokko_desktop.model.*;
import com.msyconseil.bokko_desktop.utils.adapterType.*;

public class GsonConfig {
    public static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(UserModel.class, new UserModelTypeAdapter())
                .registerTypeAdapter(VehiculeModel.class, new VehiculeModelTypeAdapter())
                .registerTypeAdapter(ProfilModel.class, new ProfilModelTypeAdapter())
                .registerTypeAdapter(TrajetModel.class, new TrajetModelTypeAdapter())
                .registerTypeAdapter(ReservationModel.class, new ReservationModelTypeAdapter())
                .registerTypeAdapter(AvisModel.class, new AvisModelTypeAdapter())
                .registerTypeAdapter(MessageModel.class, new MessageModelTypeAdapter())
                .setPrettyPrinting()
                .create();
    }
}
