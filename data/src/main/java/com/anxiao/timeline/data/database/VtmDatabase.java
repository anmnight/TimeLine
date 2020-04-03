package com.anxiao.timeline.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.anxiao.timeline.data.database.dao.BranchDao;
import com.anxiao.timeline.data.database.dao.CitiesDao;
import com.anxiao.timeline.data.database.dao.OrderInfoDao;
import com.anxiao.timeline.data.database.po.Branch;
import com.anxiao.timeline.data.database.vo.BranchInfo;
import com.anxiao.timeline.data.database.po.City;
import com.anxiao.timeline.data.database.vo.ContactInfo;
import com.anxiao.timeline.data.database.vo.JobInfo;
import com.anxiao.timeline.data.database.vo.OrderInfo;
import com.anxiao.timeline.data.database.vo.UserInfo;


@Database(entities = {OrderInfo.class, BranchInfo.class, ContactInfo.class, JobInfo.class, UserInfo.class, City.class, Branch.class}, version = 1, exportSchema = false)
public abstract class VtmDatabase extends RoomDatabase {

    public abstract BranchDao branchDao();

    public abstract CitiesDao citiesDao();

    public abstract OrderInfoDao orderInfoDao();

}
