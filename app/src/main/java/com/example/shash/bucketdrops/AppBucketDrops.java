package com.example.shash.bucketdrops;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by shash on 12/14/2017.
 */

public class AppBucketDrops extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
