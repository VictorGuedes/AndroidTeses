package com.example.vitu.projetotese.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.vitu.projetotese.model.UserBanco;


@Database(entities = {UserBanco.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO getUserDAO();


}
