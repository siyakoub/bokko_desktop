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
import com.msyconseil.bokko_desktop.model.ProfilModel;
import com.msyconseil.bokko_desktop.model.ReservationModel;
import com.msyconseil.bokko_desktop.utils.adapterType.ApiListResponse;

public class ProfilService extends AbstractService {

    private HttpClient httpClient = HttpClient.newHttpClient();

    private Gson gson = new Gson();

    public ProfilModel get(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/profil/?email=" + email))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ProfilModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Profil Réponse HTTP non réussie :" + response.statusCode());
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

    public ProfilModel getAProfil(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type" , "application/json")
                    .uri(URI.create(baseUrl + "/profil/getaprofil?email=" + email))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ProfilModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Profil Réponse HTTP non réussie :" + response.statusCode());
                return null;
            } else {
                System.out.println("Réponse HTTP non réussie : " + response.statusCode());
                return null;
            }
        } catch (Exception e){
            e.fillInStackTrace();
            return null;
        }
    }

    public List<ProfilModel> getAll(String token, int page, int size) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/profil/all?page=" + page + "&size=" + size))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<ApiListResponse<ProfilModel>>() {}.getType();
                ApiListResponse<ProfilModel> apiResponse = gson.fromJson(responseBody, responseType);
                return apiResponse.getContent();
            } else if (response.statusCode() == 404) {
                System.out.println("Profil Réponse HTTP non réussie :" + response.statusCode());
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

    public ProfilModel register(Map<String, String> profilRegister) {
        try {
            String json = gson.toJson(profilRegister);
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/profil/register"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ProfilModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Profil Réponse HTTP non réussie :" + response.statusCode());
                return null;
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

    public ProfilModel create(String token, ProfilModel profilModel) {
        try {
            String json = gson.toJson(profilModel);
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/profil/"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ProfilModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Profil Réponse HTTP non réussie :" + response.statusCode());
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

    public ProfilModel update(String token, String email, ProfilModel profilModel) {
        try {
            String json = gson.toJson(profilModel);
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/profil/?email=" + email))
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ProfilModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("Profil Réponse HTTP non réussie :" + response.statusCode());
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

    public ProfilModel delete(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/profil/?email=" + email))
                    .DELETE()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), ProfilModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("User Réponse HTTP non réussie :" + response.statusCode());
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
