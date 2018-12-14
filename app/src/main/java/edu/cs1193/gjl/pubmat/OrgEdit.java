package edu.cs1193.gjl.pubmat;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.io.FileOutputStream;
import java.io.IOException;

import edu.cs1193.gjl.pubmat.realmObjects.Organization;

@EActivity(R.layout.activity_org_edit)
public class OrgEdit extends AppCompatActivity {

    @ViewById
    ImageButton orgImage;

    @ViewById
    TextView orgNameTV;

    @ViewById
    EditText orgName;

    @ViewById
    TextView orgDescTV;

    @ViewById
    EditText orgDesc;

    @ViewById
    Button saveButton;

    @Extra
    String orgID; //get orgName from Edit Button from OrgProfile!! //orgID == orgName (?)

    @Bean
    RealmOperator realmOperator;


    Organization beingEdited;

    @AfterViews
    public void init() {
        beingEdited = realmOperator.realm.where(Organization.class).equalTo("orgID", orgID).findFirst();
        saveButton.setText("Save Changes");
        orgName.setText(beingEdited.getOrgName());
        orgDesc.setText(beingEdited.getOrgDesc());
        refreshImageButton(beingEdited.getOrgPicPath(), orgImage);
    }

    @Click(R.id.saveButton)
    public void saveButtonAction() {
        String nameTemp = orgName.getText().toString();
        String descTemp = orgDesc.getText().toString();

        Organization org = realmOperator.realm.copyFromRealm(beingEdited);
        org.setOrgName(nameTemp);
        org.setOrgDesc(descTemp);

        realmOperator.saveOrg(org);
        Toast t = Toast.makeText(this, "Org profile edited", Toast.LENGTH_LONG);
        t.show();
        OrgProfile_.intent(this).start();
        finish();
    }


    public void editImage(View v) { ImageActivity_.intent(this).startForResult(0); }

    public void onActivityResult(int requestCode, int responseCode, Intent data)
    {
        if (requestCode==0)
        {
            if (responseCode==100)
            {
                // save rawImage to file savedImage.jpeg
                // load file via picasso
                byte[] jpeg = data.getByteArrayExtra("rawJpeg");

                try {
                    File savedImage = saveFile(jpeg);
                    //refreshImageView(savedImage);

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

        File savedImage = new File(getImageDir, orgID + "savedImage.jpeg");

        FileOutputStream fos = new FileOutputStream(savedImage);
        fos.write(jpeg);
        fos.close();
        return savedImage;
    }

    public void onDestroy()
    {
        super.onDestroy();
        realmOperator.close();
    }

    public void refreshImageButton(String savedImagePath, ImageButton imageButton) {
        File getImageDir = getExternalCacheDir();
        File savedImage = new File(getImageDir, savedImagePath);

        if (savedImage.exists()) {
            Picasso.with(this)
                    .load(savedImage)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(imageButton);
        }
    }

}
