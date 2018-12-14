package edu.cs1193.gjl.pubmat.realmObjects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Organization extends RealmObject{

    @PrimaryKey
    private int orgID;

    private String orgName;
    private String orgDesc;
    private String orgShortDesc;
    private String orgPostIDs; //Use gson/json to get the ArrayList
    private String orgPicPath;
    private int postCount; //used to make IDs for OrgPosts

    public int getOrgID() {
        return orgID;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public String getOrgShortDesc() {
        return orgShortDesc;
    }

    public void setOrgShortDesc(String orgShortDesc) {
        this.orgShortDesc = orgShortDesc;
    }

    public String getOrgPostIDs() {
        return orgPostIDs;
    }

    public void setOrgPostIDs(String orgPostIDs) {
        this.orgPostIDs = orgPostIDs;
    }

    public String getOrgPicPath() { return orgPicPath; }

    public void setOrgPicPath(String orgPicPath) { this.orgPicPath = orgPicPath; }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }
}
