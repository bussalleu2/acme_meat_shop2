package com.bussalleu.acme2;

/**
 * Created by alba on 5/2/17.
 */

public class Photo {

    private String id;
    private String userID;
    private String secretKey;
    private String server;
    private String farm;
    private String url;
    private String title;

    public Photo(String id, String userID, String secretKey, String server, String farm, String url, String title) {
        this.id = id;
        this.userID = userID;
        this.secretKey = secretKey;
        this.server = server;
        this.farm = farm;
        this.url = url;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
