package com.example.vitu.projetotese.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.example.vitu.projetotese.model.UserBanco;

import java.util.List;

/**
 * Created by vitu on 20/06/2018.
 */
@Dao
public interface UserDAO {

    @Insert
    public void insert(UserBanco... users);

    @Delete
    public void delete(UserBanco userBanco);

    @Query("SELECT * FROM User")
    public UserBanco getLogedUser();

}
