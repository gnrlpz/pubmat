package edu.cs1193.gjl.pubmat.realmObjects;

import io.realm.annotations.PrimaryKey;

public class OrgPost {

    @PrimaryKey
    private int postID;

    private int originalPoster; //is an OrgID

    private String postPhoto;
    private String postCaption;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getOriginalPoster() {
        return originalPoster;
    }

    public void setOriginalPoster(int originalPoster) {
        this.originalPoster = originalPoster;
    }

    public String getPostPhoto() {
        return postPhoto;
    }

    public void setPostPhoto(String postPhoto) {
        this.postPhoto = postPhoto;
    }

    public String getPostCaption() {
        return postCaption;
    }

    public void setPostCaption(String postCaption) {
        this.postCaption = postCaption;
    }
}
