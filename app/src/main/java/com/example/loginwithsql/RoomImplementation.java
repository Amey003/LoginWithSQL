package com.example.loginwithsql;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.loginwithsql.data.LocalDB;

public class RoomImplementation extends Application {
    private static RoomImplementation mInstance;
    private LocalDB dbInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        dbInstance = Room.databaseBuilder(getApplicationContext(), LocalDB.class, "LocalDB").build();
    }

    public static RoomImplementation getmInstance() {
        return mInstance;
    }

    public LocalDB getDbInstance() {
        return dbInstance;
    }
}
