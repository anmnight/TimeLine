package com.anxiao.timeline.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.anxiao.timeline.data.database.dao.NewsDao;
import com.anxiao.timeline.data.database.dao.UserDao;
import com.anxiao.timeline.data.database.po.City;
import com.anxiao.timeline.data.vo.News;
import com.anxiao.timeline.data.vo.UserInfo;


@Database(entities = {UserInfo.class, City.class, News.class}, version = 1, exportSchema = false)
public abstract class TimeLineDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract NewsDao newsDao();


}
