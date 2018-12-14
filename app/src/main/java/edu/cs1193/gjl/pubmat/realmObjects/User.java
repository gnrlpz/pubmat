package edu.cs1193.gjl.pubmat.realmObjects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    @PrimaryKey
    private String userID;

    private String username;
    private String password;

    private String subscriptions; //please use gson/json to convert the array
    private String memberships; //please use gson/json to convert the array
    private String orgName;

    private String imagePath;

    public User() { }

    public User(String username, String orgName, String password) {
        this.username = username;
        this.password = password;
        this.orgName = orgName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(String subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getMemberships() {
        return memberships;
    }

    public void setMemberships(String memberships) {
        this.memberships = memberships;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String position) {
        this.orgName = position;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
