package com.anxiao.timeline.data.database.vo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author: anxiao
 * @version: V1.0
 * @project: MatmMobile
 * @package: com.chinasofti.matmmobile.data.model
 * @description: description
 * @date: 2019-12-31
 * @time: 12:04
 */
@Entity(tableName = "order_info")
public class OrderInfo {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "country_code")
    public String countryCode;

    @ColumnInfo(name = "tel")
    public String tel;

    @ColumnInfo(name = "order_id")
    public String orderId;

    @ColumnInfo(name = "status")
    public String status;

}
