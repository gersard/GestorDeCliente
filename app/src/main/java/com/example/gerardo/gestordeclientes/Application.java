package com.example.gerardo.gestordeclientes;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Gerardo on 23/07/2016.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
