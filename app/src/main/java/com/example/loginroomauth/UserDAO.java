package com.example.loginroomauth;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {
    @Insert
    void inserer(User user);

    @Query("SELECT * FROM user WHERE name = :nom AND password = :password")
    User verifierLogin(String nom, String password);
}
