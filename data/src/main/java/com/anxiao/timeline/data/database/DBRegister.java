package com.anxiao.timeline.data.database;

import android.app.Application;

import androidx.room.Room;

import com.facebook.stetho.Stetho;


public class DBRegister {

    private static TimeLineDatabase DATABASE;

    public static void init(Application application,String dbName) {
        //register database
        DATABASE = Room.databaseBuilder(application, TimeLineDatabase.class, dbName)
                .build();
        //init stetho
        Stetho.initializeWithDefaults(application);
    }

    public static TimeLineDatabase db() {
        return DATABASE;
    }


}
