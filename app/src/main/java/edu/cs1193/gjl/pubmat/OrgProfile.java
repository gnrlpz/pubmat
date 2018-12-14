package edu.cs1193.gjl.pubmat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
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

import edu.cs1193.gjl.pubmat.realmObjects.OrgPost;
import edu.cs1193.gjl.pubmat.realmObjects.Organization;
import io.realm.RealmResults;

@EActivity(R.layout.activity_org_profile)
public class OrgProfile extends AppCompatActivity {

    @ViewById
    ListView feedList;

    @ViewById
    ImageView orgDP;

    @ViewById
    TextView orgName;

    @ViewById
    Button editButton;

    @Extra
    String orgID; //get orgID from User clicking on Org Profile!!! //orgID == orgName !!!

    @Bean
    RealmOperator realmOperator;

    ArrayList<OrgPost> feedPosts = new ArrayList<OrgPost>();
    PostAdapter postAdapter;
    Organization thisOrg;

    @AfterViews
    public void init() {
        thisOrg = realmOperator.realm.where(Organization.class).equalTo("orgID", orgID).findFirst();
        orgName.setText(thisOrg.getOrgName());
        refreshImageView(thisOrg.getOrgPicPath(), orgDP);

        feedPosts = realmOperator.getPostsfromOrg(orgID);

        postAdapter = new PostAdapter(this, feedPosts);

        feedList.setAdapter(postAdapter);
    }

    @Click(R.id.editButton)
    public void editButtonAction() { OrgEdit_.intent(this).orgID(thisOrg.getOrgName()).start(); }

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

    public Organization getOrgByName(String orgName)
    {
        return realmOperator.getOrgByName(orgName);
    }

}
