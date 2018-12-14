package edu.cs1193.gjl.pubmat;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import edu.cs1193.gjl.pubmat.realmObjects.OrgPost;
import edu.cs1193.gjl.pubmat.realmObjects.Organization;

@EActivity(R.layout.make_post)
public class MakePost extends AppCompatActivity {

    @Bean
    RealmOperator ro;

    @ViewById
    ImageView postImage;

    @ViewById
    EditText postDesc;

    @Extra
    Organization orgPoster; //Pass an Org into this

    String postPhoto = "";

    @Click(R.id.postButton)
    public void onClickPost()
    {
        String curPostDesc = postDesc.getText().toString();
        //Save image, somehow?

        OrgPost or = new OrgPost();

        int postCount = orgPoster.getPostCount();
        or.setPostID(postCount + 1);

        orgPoster.setPostCount(postCount + 1);

        or.setPostPhoto(postPhoto);
        or.setPostCaption(curPostDesc);
        or.setOriginalPoster(orgPoster.getOrgID());

        ro.saveOrg(orgPoster);
        ro.saveOrgPost(or);
    }

    @Click(R.id.uploadPic)
    public void onClickImage()
    {
        // Call Image Activity
    }

}
