package edu.cs1193.gjl.pubmat.realmObjects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class OrgPost extends RealmObject {

    @PrimaryKey
    private int postID;

    private String originalPoster; //is an OrgName

    private String postPhoto;
    private String postCaption;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getOriginalPoster() {
        return originalPoster;
    }

    public void setOriginalPoster(String originalPoster) {
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
