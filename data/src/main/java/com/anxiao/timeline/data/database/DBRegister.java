package com.anxiao.timeline.data.database;

import android.app.Application;

import androidx.room.Room;

public class DBRegister {

    private static TimeLineDatabase DATABASE;

    public static void init(Application application) {
        //register database
        DATABASE = Room.databaseBuilder(application, TimeLineDatabase.class, "timeline_db").build();
    }

    public static TimeLineDatabase db() {
        return DATABASE;
    }
}
