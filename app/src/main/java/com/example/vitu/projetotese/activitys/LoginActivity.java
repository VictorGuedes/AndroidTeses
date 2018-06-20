package com.example.vitu.projetotese.activitys;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vitu.projetotese.DAO.AppDatabase;
import com.example.vitu.projetotese.DAO.UserDAO;
import com.example.vitu.projetotese.MainActivity;
import com.example.vitu.projetotese.R;
import com.example.vitu.projetotese.app.App;
import com.example.vitu.projetotese.model.ResponseToken;
import com.example.vitu.projetotese.model.User;
import com.example.vitu.projetotese.model.UserBanco;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLogin;
    private EditText editTextPassword;
    private Toast toast;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLogin = (EditText) findViewById(R.id.login);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
    }

    public void cliBotao(View view) {
        if (editTextLogin.getText().toString().isEmpty() && editTextPassword.getText().toString().isEmpty()){
            toast = Toast.makeText(this, "Insira todos os campos para continuar", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }else {
            progressBar.setVisibility(View.VISIBLE);
            final Call<ResponseToken> requestToken = App.getRestClient().getTokenEndpoint().retornarUserToken(
                    "password",
                    editTextLogin.getText().toString(),
                    editTextPassword.getText().toString()
            );
            requestToken.clone().enqueue(new Callback<ResponseToken>() {
                @Override
                public void onResponse(@NonNull Call<ResponseToken> call, @NonNull Response<ResponseToken> response) {
                    if(!response.isSuccessful()){
                        progressBar.setVisibility(View.INVISIBLE);
                        toast = Toast.makeText(LoginActivity.this, "Email ou Senha incorreto", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }else{
                        ResponseToken responseToken = response.body();
                        Log.i("Insert",
                            responseToken.getAccess_token()
                            + "\n" + responseToken.getUserName());

                        gravarUser(responseToken.getAccess_token());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseToken> call, @NonNull Throwable t) {
                    progressBar.setVisibility(View.INVISIBLE);
                    toast = Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });
        }
    }


    public void gravarUser(final String token){

        Call<User> requestUserInfo = App.getRestClient().getTokenEndpoint().retornarUserInfo("Bearer " + token);

        requestUserInfo.clone().enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if(!response.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    toast = Toast.makeText(LoginActivity.this, "Erro ao criar Usuario local", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else {
                    User responseUser = response.body();
                    UserBanco userBanco = new UserBanco();
                    if(responseUser != null){
                        /*AppDatabase database = Room.databaseBuilder(LoginActivity.this, AppDatabase.class, "user")
                                .allowMainThreadQueries()
                                .build();
                        UserDAO userDAO = database.getUserDAO();*/
                        UserDAO userDAO = App.getDatabase().getUserDAO();

                        userBanco.setId(responseUser.getId());
                        userBanco.setEMAIL(responseUser.getEmail());
                        userBanco.setToken("Bearer " + token);
                        Calendar tokenExpiracao = Calendar.getInstance();
                        tokenExpiracao.add(Calendar.DATE, +60);
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                        userBanco.setDataExpiracaoToken(formato.format(tokenExpiracao.getTime()));

                        userDAO.insert(userBanco);
                        progressBar.setVisibility(View.INVISIBLE);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                toast = Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });
    }

}
