package com.msyconseil.bokko_desktop.service;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.msyconseil.bokko_desktop.model.ReservationModel;
import com.msyconseil.bokko_desktop.model.VehiculeModel;
import com.msyconseil.bokko_desktop.utils.adapterType.ApiListResponse;

public class VehiculeService extends AbstractService{

    private HttpClient httpClient = HttpClient.newHttpClient();

    private Gson gson = new Gson();

    public VehiculeModel get(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/vehicule/?email=" + email))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), VehiculeModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Véhicule Réponse HTTP non réussie :" + response.statusCode());
                return null;
            } else {
                System.out.println("Réponse HTTP non réussie : " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public List<VehiculeModel> getAll(String token, int page, int size) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/vehicule/all?page=" + page + "&size=" + size))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<ApiListResponse<VehiculeModel>>() {}.getType();
                ApiListResponse<VehiculeModel> apiResponse = gson.fromJson(responseBody, responseType);
                return apiResponse.getContent();
            } else if (response.statusCode() == 404) {
                System.out.println("Véhicule Réponse HTTP non réussie :" + response.statusCode());
                return null;
            } else {
                System.out.println("Réponse HTTP non réussie : " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public VehiculeModel create(String token, VehiculeModel vehiculeModel) {
        try {
            String json = gson.toJson(vehiculeModel);
            HttpRequest request = HttpRequest.newBuilder()
                   .header("token", token)
                   .header("Content-Type", "application/json")
                   .uri(URI.create(baseUrl + "/vehicule/"))
                   .POST(HttpRequest.BodyPublishers.ofString(json))
                   .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), VehiculeModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Véhicule Réponse HTTP non réussie :" + response.statusCode());
                return null;
            } else {
                System.out.println("Réponse HTTP non réussie : " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public VehiculeModel update(String token, VehiculeModel vehiculeModel, String email) {
        try {
            String json = gson.toJson(vehiculeModel);
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/vehicule/?email=" + email))
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), VehiculeModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Véhicule Réponse HTTP non réussie :" + response.statusCode());
                return null;
            } else {
                System.out.println("Réponse HTTP non réussie : " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public VehiculeModel delete(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/vehicule/?email=" + email))
                    .DELETE()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), VehiculeModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Véhicule Réponse HTTP non réussie :" + response.statusCode());
                return null;
            } else {
                System.out.println("Réponse HTTP non réussie : " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }

}
