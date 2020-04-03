package com.anxiao.timeline.data.database;

import android.app.Application;

import androidx.room.Room;

public class DBRegister {

    private static VtmDatabase DATABASE;

    public static void init(Application application) {
        //register database
        DATABASE = Room.databaseBuilder(application, VtmDatabase.class, "mvtm_db").build();
    }

    public static VtmDatabase db() {
        return DATABASE;
    }
}
