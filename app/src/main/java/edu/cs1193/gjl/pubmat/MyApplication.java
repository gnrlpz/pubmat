package edu.cs1193.gjl.pubmat;

import android.app.Application;

import io.realm.Realm;

public class MyApplication extends Application {

    public void onCreate()
    {
        super.onCreate();

        Realm.init(this);
    }

}
