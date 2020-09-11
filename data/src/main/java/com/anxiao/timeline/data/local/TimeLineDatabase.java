package com.anxiao.timeline.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.anxiao.timeline.data.local.dao.HarvardImageDao;
import com.anxiao.timeline.data.vo.HarvardImage;


@Database(entities = {HarvardImage.class}, version = 1, exportSchema = false)
public abstract class TimeLineDatabase extends RoomDatabase {

    public abstract HarvardImageDao harvardImageDao();


}
