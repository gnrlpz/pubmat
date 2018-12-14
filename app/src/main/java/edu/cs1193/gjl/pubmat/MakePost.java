package edu.cs1193.gjl.pubmat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.cs1193.gjl.pubmat.realmObjects.OrgPost;
import edu.cs1193.gjl.pubmat.realmObjects.Organization;

@EActivity(R.layout.make_post)
public class MakePost extends AppCompatActivity {

    @Bean
    RealmOperator ro;

    @ViewById
    ImageView postImg;

    @ViewById
    EditText postDesc;

    @Extra
    String orgName; //Pass an OrgName into this //changed Organization to String

    String postPhoto = "";

    @Click(R.id.postButton)
    public void onClickPost()
    {
        String curPostDesc = postDesc.getText().toString();
        //Save image, somehow?

        OrgPost or = new OrgPost();
        Organization orgPoster = ro.getOrgByName(orgName);

        int postCount = orgPoster.getPostCount();
        or.setPostID(postCount + 1);

        orgPoster.setPostCount(postCount + 1);

        or.setPostPhoto(postPhoto);
        or.setPostCaption(curPostDesc);
        or.setOriginalPoster(orgPoster.getOrgName());

        ro.saveOrg(orgPoster);
        ro.saveOrgPost(or);
    }

    @Click(R.id.uploadPic)
    public void onClickImage()
    {
        ImageActivity_.intent(this).startForResult(0);
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data)
    {
        if (requestCode==0)
        {
            if (responseCode==100)
            {
                // save rawImage to file savedImage.jpeg
                // load file via picasso
                Organization  o = ro.getOrgByName(orgName);
                byte[] jpeg = data.getByteArrayExtra((o.getPostCount()+1)+"image.jpg");

                try {
                    File savedImage = saveFile(jpeg);
                    refreshImage(savedImage);

                    //imageUri = Uri.fromFile(savedImage);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
    }

    @NonNull
    private File saveFile(byte[] jpeg) throws IOException {
        File getImageDir = getExternalCacheDir();


        ////File savedImage = new File(getImageDir, Integer.toString(position) + "savedImage.jpeg");

        File savedImage = new File(getImageDir, orgName + "savedImage.jpeg");

        FileOutputStream fos = new FileOutputStream(savedImage);
        fos.write(jpeg);
        fos.close();
        return savedImage;
    }

    private void refreshImage(File savedImage) {
        Picasso.with(this)
                .load(savedImage)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(postImg);
    }

}
