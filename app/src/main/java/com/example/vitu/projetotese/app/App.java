package com.example.vitu.projetotese.app;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.vitu.projetotese.DAO.AppDatabase;
import com.example.vitu.projetotese.rest.RestClient;

/**
 * Created by vitu on 18/06/2018.
 */

public class App extends Application {

    private static RestClient restClient;
    private static AppDatabase database;

    @Override
    public void onCreate()
    {
        super.onCreate();
        restClient = new RestClient();
        database = Room.databaseBuilder(this, AppDatabase.class, "user")
                .allowMainThreadQueries()
                .build();
    }

    public static RestClient getRestClient()
    {
        return restClient;
    }

    public static AppDatabase getDatabase(){return database;}
}
