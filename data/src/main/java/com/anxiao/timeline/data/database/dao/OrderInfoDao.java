package com.anxiao.timeline.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.anxiao.timeline.data.database.vo.OrderInfo;

/**
 * @author: anxiao
 * @version: V1.0
 * @project: MatmMobile
 * @package: com.chinasofti.matmmobile.data.database.dao
 * @description: description
 * @date: 2019-12-31
 * @time: 12:00
 */
@Dao
public interface OrderInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(OrderInfo orderInfo);


    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void update(OrderInfo orderInfo);


    @Query("SELECT * FROM ORDER_INFO LIMIT 1")
    public OrderInfo findOrder();

    @Query("DELETE FROM ORDER_INFO")
    public void clear();

}
