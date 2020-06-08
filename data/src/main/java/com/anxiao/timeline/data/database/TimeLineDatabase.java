package com.anxiao.timeline.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.anxiao.timeline.data.database.dao.NewsDao;
import com.anxiao.timeline.data.vo.News;


@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class TimeLineDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();


}
