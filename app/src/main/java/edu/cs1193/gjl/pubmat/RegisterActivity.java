package edu.cs1193.gjl.pubmat;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import edu.cs1193.gjl.pubmat.realmObjects.User;
import io.realm.RealmResults;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    @ViewById
    EditText name;

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    @ViewById
    Button registerButton;

    @Bean
    RealmOperator realmManager;

    Uri imageuri;
    String unid;

    @AfterViews
    public void init() {
        unid = UUID.randomUUID().toString();
    }

    @Click(R.id.registerButton)
    public void registerButtonAction() {
        String nameTemp = name.getText().toString();
        String position = email.getText().toString();
        String passTemp = password.getText().toString();

        RealmResults<User> results = realmManager.realm.where(User.class).equalTo("username", nameTemp).findAll();
        if(results.size() != 0 ) {
            Toast t = Toast.makeText(this, "Username not available", Toast.LENGTH_LONG);
            t.show();
            return;
        }

        if (nameTemp.equals("") || passTemp.equals("")) {
            Toast t = Toast.makeText(this, "Username and Password required.", Toast.LENGTH_LONG);
            t.show();
        } else {
            System.out.println("create character case");

            User u = new User(nameTemp, position, passTemp);

            u.setUserID(unid);
            System.out.println("generated: " + unid + "\nSet: " + u.getUserID());
            u.setImagePath(unid + "savedImage.jpeg");
            realmManager.saveUser(u);

            Toast t = Toast.makeText(this, "Account created", Toast.LENGTH_LONG);
            t.show();
            LoginActivity_.intent(this).start();
            finish();
        }

    }

    @Click(R.id.cancelButton)
    public void cancelButtonAction() {
        LoginActivity_.intent(this).start();
        finish();
    }

    @Click(R.id.imageButton)
    public void imageButtonAction() { ImageActivity_.intent(this).startForResult(0); }

    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode==0) {
            if (responseCode==100) {
                byte[] jpeg = data.getByteArrayExtra("rawJpeg");

                try {
                    File savedImage = saveFile(jpeg);
                    imageuri = Uri.fromFile(savedImage);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @NonNull
    private File saveFile(byte[] jpeg) throws IOException {
        File getImageDir = getExternalCacheDir();
        System.out.println("saveFile: " + unid);

        File savedImage = new File(getImageDir, unid + "savedImage.jpeg");

        FileOutputStream fos = new FileOutputStream(savedImage);
        fos.write(jpeg);
        fos.close();
        return savedImage;
    }

    public void onDestroy() {
        super.onDestroy();
        realmManager.close();
    }

}