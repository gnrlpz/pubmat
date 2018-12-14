package edu.cs1193.gjl.pubmat;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.EBean;

import java.lang.reflect.Type;
import java.util.ArrayList;

import edu.cs1193.gjl.pubmat.realmObjects.User;

import static android.content.Context.MODE_PRIVATE;

@EBean
public class SharedPrefOperator {

    SharedPreferences.Editor editUserData;
    SharedPreferences userData;

    SharedPreferences.Editor editRemPrefs;
    SharedPreferences remPrefs;

    Gson gson = new GsonBuilder().create();
    ArrayList<User> currentUsers;
    Context context;


    public SharedPrefOperator(Context context) {
        this.context = context;
    }


    void loadUserSharedPrefs() {
        userData = context.getSharedPreferences("userData", MODE_PRIVATE);
        editUserData = userData.edit();
    }

    void loadRemSharedPrefs() {
        remPrefs = context.getSharedPreferences("userPrefs", MODE_PRIVATE);
        editRemPrefs = remPrefs.edit();
    }

    // move these?
    ArrayList<User> parseUsers() {
        Type collectionType = new TypeToken<ArrayList<User>>() {}.getType();
        ArrayList<User> currentUsers = gson.fromJson(userData.getString("users", "[]"), collectionType);
        return currentUsers;
    }

    Boolean checkRemember() {
        return remPrefs.getBoolean("isRemembered", false);
    }

    void addToRemPrefs(Boolean rem, String uname, String pass) {
        editRemPrefs.putBoolean("isRemembered", rem);
        editRemPrefs.putString("rememberedUser", uname);
        editRemPrefs.putString("rememberedPass", pass);
        editRemPrefs.commit();
    }

    String getFromRemPrefs(String prefToLoad) {
        return remPrefs.getString(prefToLoad, "");
    }

    String getFromRemPrefs2(String prefToLoad) {
        return remPrefs.getString(prefToLoad, null);
    }


    void updateUserSharedPrefs(ArrayList<User> currentUsers) {
        String updatedUsersString = gson.toJson(currentUsers);
        editUserData.putString("users", updatedUsersString);
        editUserData.commit();
    }

}
