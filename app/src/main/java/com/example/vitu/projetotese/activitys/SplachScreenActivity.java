package com.example.vitu.projetotese.activitys;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vitu.projetotese.DAO.AppDatabase;
import com.example.vitu.projetotese.DAO.UserDAO;
import com.example.vitu.projetotese.MainActivity;
import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.app.App;
import com.example.vitu.projetotese.model.UserBanco;

public class SplachScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splach_screen);

        UserDAO userDAO = App.getDatabase().getUserDAO();
        UserBanco userBanco = userDAO.getLogedUser();
        if(userBanco != null){
            startActivity(new Intent(this, MainActivity.class));
        }else{
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
