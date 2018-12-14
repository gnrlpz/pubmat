package edu.cs1193.gjl.pubmat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import edu.cs1193.gjl.pubmat.realmObjects.User;
import io.realm.RealmResults;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    @ViewById
    EditText username;

    @ViewById
    EditText password;

    @ViewById
    CheckBox rememberMe;

    @ViewById
    Button loginButton;

    @ViewById
    Button registerButton;

    @Bean
    RealmOperator realmManager;

    SharedPrefOperator dmc;

    @AfterViews
    public void init() {
        dmc = new SharedPrefOperator(getApplicationContext());
        dmc.loadRemSharedPrefs();

        if(dmc.checkRemember()) {
            username.setText(dmc.getFromRemPrefs("rememberedUser"));
            password.setText(dmc.getFromRemPrefs("rememberedPass"));
        }
    }

    @Click(R.id.loginButton)
    public void loginAction() {
        String uname = username.getText().toString();
        String pass = password.getText().toString();
        Boolean rem = rememberMe.isChecked();

        Boolean correct = false;

        RealmResults<User> results = realmManager.realm.where(User.class).equalTo("username", uname).equalTo("password", pass).findAll();
        if(results.size() > 0) {
            if (rem) {
                dmc.addToRemPrefs(rem, uname, pass);
            } else {
                dmc.addToRemPrefs(false, null, null);
            }
            correct = true;
//            MainActivity_.intent(this).logMeIn(uname).start();
            NewsFeed_.intent(this).userName(uname).start();
            finish();
        }

        if (!correct) {
            Toast t = Toast.makeText(this, "Username and/or Password is incorrect.", Toast.LENGTH_LONG);
            t.show();
        }
    }

    @Click(R.id.registerButton)
    public void registerButton() {
        RegisterActivity_.intent(this).start();
    }

    public void onDestroy() {
        super.onDestroy();
        realmManager.close();
    }

}
