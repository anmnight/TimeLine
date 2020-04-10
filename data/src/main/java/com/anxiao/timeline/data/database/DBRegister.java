package com.anxiao.timeline.data.database;

import android.app.Application;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

public class DBRegister {

    public static TimeLineDatabase DATABASE;

    public static void init(Application application) {
        //register database
        DATABASE = Room.databaseBuilder(application, TimeLineDatabase.class, "timeline_db")
                .build();
        //init stetho
        Stetho.initializeWithDefaults(application);
    }

    public static TimeLineDatabase db() {
        return DATABASE;
    }


}
