package edu.cs1193.gjl.pubmat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;

import edu.cs1193.gjl.pubmat.realmObjects.User;

@EActivity(R.layout.activity_user_profile)
public class UserProfileActivity extends AppCompatActivity {
    @ViewById
    ListView feedList;

    @ViewById
    ImageView userDP;

    @ViewById
    TextView userName;

    @ViewById
    Button editButton;

    @Extra
    String userID; //get orgID from User clicking on Org Profile!!! //orgID == orgName !!!

    @Bean
    RealmOperator realmOperator;

//    ArrayList<OrgPost> feedPosts = new ArrayList<OrgPost>();
    PostAdapter postAdapter;
    User thisUser;

    @AfterViews
    public void init() {
        thisUser = realmOperator.realm.where(User.class).equalTo("userID", userID).findFirst();
        userName.setText(thisUser.getOrgName());
        refreshImageView(thisUser.getImagePath(), userDP);

//        feedPosts = realmOperator.getPostsfromOrg(orgID);

//        postAdapter = new PostAdapter(this, feedPosts);

//        feedList.setAdapter(postAdapter);
    }

    @Click(R.id.editButton)
    public void editButtonAction() { RegisterActivity_.intent(this).userIDforEdit(thisUser.getUsername()).start(); }

    public void refreshImageView(String savedImagePath, ImageView imageView) {
        File getImageDir = getExternalCacheDir();
        File savedImage = new File(getImageDir, savedImagePath);

        if (savedImage.exists()) {
            Picasso.with(this)
                    .load(savedImage)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(imageView);
        }
    }

//    public Organization getOrgByName(String orgName)
//    {
//        return realmOperator.getOrgByName(orgName);
//    }
}
