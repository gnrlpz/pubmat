package edu.cs1193.gjl.pubmat;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import edu.cs1193.gjl.pubmat.realmObjects.OrgPost;
import edu.cs1193.gjl.pubmat.realmObjects.Organization;

@EActivity(R.layout.newsfeed_main)
public class NewsFeed extends AppCompatActivity {

    @ViewById
    ListView feed;

    @ViewById (R.id.UserName)
    TextView unField;

    @Bean
    RealmOperator ro;

    ArrayList<OrgPost> feedPosts = new ArrayList<OrgPost>();
    FeedAdapter adapter;

    @Extra
    String userName;

    String orgName;

    @AfterViews
    public void init()
    {
        //data
        orgName = ro.getUserByName(userName).getOrgName();
        OrgPost op1 = new OrgPost();
        op1.setOriginalPoster("ORG");
        op1.setPostCaption("This is a test");
        op1.setPostID(1);
        op1.setPostPhoto("sample photo path");

        feedPosts.add(op1);

        unField.setText(userName);

        //adapter
        adapter = new FeedAdapter(this, feedPosts);
        feed.setAdapter(adapter);

    }

    public Organization getOrgByName(String orgName)
    {
        return ro.getOrgByName(orgName);
    }

    @Click(R.id.postButton)
    public void onClickMakePost()
    {
        MakePost_.intent(this)
                .orgName(orgName)
                .start();
    }

    @Click(R.id.profileButton)
    public void onClickProfileButton()
    {
        UserProfileActivity_.intent(this)
                .un(ro.getUserByName(userName).getUsername())
                .start();
    }

}
