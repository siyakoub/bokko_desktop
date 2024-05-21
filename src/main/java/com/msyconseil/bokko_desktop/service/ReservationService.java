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
import com.msyconseil.bokko_desktop.utils.adapterType.ApiListResponse;

public class ReservationService extends AbstractService {

    private HttpClient httpClient = HttpClient.newHttpClient();

    private Gson gson = new Gson();

    public ReservationModel get(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/booking/?email=" + email))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ReservationModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Réservation Réponse HTTP non réussie :" + response.statusCode());
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

    public List<ReservationModel> getAll(String token, String page, String size) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/booking/all?page=" + page + "&size=" + size))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<ApiListResponse<ReservationModel>>() {}.getType();
                ApiListResponse<ReservationModel> apiResponse = gson.fromJson(responseBody, responseType);
                return apiResponse.getContent();
            } else {
                System.out.println("Réponse HTTP non réussie : " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ReservationModel create(String token, ReservationModel reservationModel) {
        try {
            String json = gson.toJson(reservationModel);
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/booking/"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ReservationModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Réservation Réponse HTTP non réussie :" + response.statusCode());
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

    public ReservationModel update(String token, String email, ReservationModel reservationModel) {
        try {
            String json = gson.toJson(reservationModel);
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/booking/?email=" + email))
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ReservationModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Réservation Réponse HTTP non réussie :" + response.statusCode());
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

    public ReservationModel delete(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/booking/?email=" + email))
                    .DELETE()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ReservationModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Réservation Réponse HTTP non réussie :" + response.statusCode());
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
