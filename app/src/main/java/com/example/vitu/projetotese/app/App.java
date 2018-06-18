package com.example.vitu.projetotese.app;

import android.app.Application;

import com.example.vitu.projetotese.rest.RestClient;

/**
 * Created by vitu on 18/06/2018.
 */

public class App extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate()
    {
        super.onCreate();
        restClient = new RestClient();
    }

    public static RestClient getRestClient()
    {
        return restClient;
    }

}
