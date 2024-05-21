package com.msyconseil.bokko_desktop.model;

public class SessionData {
    private static SessionData instance;
    private UserModel adminData;

    private SessionData() {}

    public static SessionData getInstance() {
        if (instance == null) {
            instance = new SessionData();
        }
        return instance;
    }

    public UserModel getAdminData() {
        return adminData;
    }

    public void setAdminData(UserModel adminData) {
        this.adminData = adminData;
    }
}
