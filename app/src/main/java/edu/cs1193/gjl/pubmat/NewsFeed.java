package edu.cs1193.gjl.pubmat;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import edu.cs1193.gjl.pubmat.realmObjects.OrgPost;
import edu.cs1193.gjl.pubmat.realmObjects.Organization;

@EActivity(R.layout.newsfeed_main)
public class NewsFeed extends AppCompatActivity {

    @ViewById
    ListView feed;

    @Bean
    RealmOperator ro;

    ArrayList<OrgPost> feedPosts = new ArrayList<OrgPost>();
    FeedAdapter adapter;

    @AfterViews
    public void init()
    {
        //data

        OrgPost op1 = new OrgPost();
        op1.setOriginalPoster("ORG");
        op1.setPostCaption("This is a test");
        op1.setPostID(1);
        op1.setPostPhoto("sample photo path");

        feedPosts.add(op1);

        //adapter
        adapter = new FeedAdapter(this, feedPosts);
        feed.setAdapter(adapter);

    }

    public Organization getOrgByName(String orgName)
    {
        return ro.getOrgByName(orgName);
    }

}
