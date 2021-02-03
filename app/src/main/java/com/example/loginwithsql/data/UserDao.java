package com.example.loginwithsql.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UserDao {
    @Insert
    void createUser(User user) ;

    @Query("SELECT * FROM User Where username like :strUsername")
     User getUserByUsername(String strUsername);



}
