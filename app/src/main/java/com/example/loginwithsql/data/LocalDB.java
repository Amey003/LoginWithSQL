package com.example.loginwithsql.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class} , version = 1)
public abstract class LocalDB extends RoomDatabase {
    public abstract UserDao userDao();

}
