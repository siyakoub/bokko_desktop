package com.msyconseil.bokko_desktop.service;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.msyconseil.bokko_desktop.model.ReservationModel;
import com.msyconseil.bokko_desktop.model.UserModel;
import com.msyconseil.bokko_desktop.utils.adapterType.ApiListResponse;
import com.msyconseil.bokko_desktop.utils.gson.GsonConfig;

public class UserService extends AbstractService {

    private HttpClient httpClient = HttpClient.newHttpClient();

    private final Gson gson = GsonConfig.createGson();

    public UserModel login(String email, String password) {
        try {
            String loginJson = gson.toJson(Map.of("email", email, "password", password));
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/user/login"))
                    .POST(HttpRequest.BodyPublishers.ofString(loginJson))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), UserModel.class);
            } else if (response.statusCode() == 404) {
                System.out.println("User Réponse HTTP non réussie :" + response.statusCode());
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

    public boolean logout(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/user/logout"))
                    .DELETE()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return true;
            } else if (response.statusCode() == 404) {
                return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    public UserModel get(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/user/?email=" + email))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), UserModel.class);
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

    public List<UserModel> getAll(String token, int page, int size) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("token", token)
                    .header("Content-Type", "application/json")
                    .uri(URI.create(baseUrl + "/user/all?page=" + page + "&size=" + size))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<ApiListResponse<UserModel>>() {}.getType();
                ApiListResponse<UserModel> apiResponse = gson.fromJson(responseBody, responseType);
                return apiResponse.getContent();
            } else if (response.statusCode() == 404) {
                System.out.println("User Réponse HTTP non réussie :" + response.statusCode());
                return null;
            } else {
                System.out.println("Réponse HTTP non réussie : " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public UserModel create(String token, UserModel userModel) {
        try {
            String json = gson.toJson(userModel);
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/user/"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), UserModel.class);
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

    public UserModel update(String token, String email, UserModel userModel) {
        try {
            String json = gson.toJson(userModel);
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/user/"))
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), UserModel.class);
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

    public UserModel delete(String token, String email) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("token", token)
                    .uri(URI.create(baseUrl + "/user/?email=" + email))
                    .DELETE()
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String responseBody = response.body().trim();
                Type responseType = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> responseMap = gson.fromJson(responseBody, responseType);
                Object content = responseMap.get("content");
                return gson.fromJson(gson.toJson(content), UserModel.class);
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
