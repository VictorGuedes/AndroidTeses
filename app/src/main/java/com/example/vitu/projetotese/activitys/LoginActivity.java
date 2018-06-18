package com.example.vitu.projetotese.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vitu.projetotese.MainActivity;
import com.example.vitu.projetotese.R;

public class LoginActivity extends AppCompatActivity {

    private Button botaoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        botaoLogin = (Button) findViewById(R.id.entrar_logar);

    }

    public void cliBotao(View view) {
        startActivity(new Intent(this, MainActivity.class));
        //finish();
    }
}
