package com.anxiao.timeline.data.database.vo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "branch_info",
        foreignKeys = @ForeignKey(entity = OrderInfo.class,
                parentColumns = "id",
                childColumns = "order_id",
                onDelete = CASCADE,
                onUpdate = CASCADE),
        indices = {
                @Index(value = ("id"), unique = true)
        })
public class BranchInfo {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "order_id")
    public long order_id;

    @ColumnInfo(name = "is_today")
    public Boolean isToday;

    @ColumnInfo(name = "appoint_time")
    public long appointmentTime;//时间戳

    @ColumnInfo(name = "appoint_time_zone")
    public String appointmentTimeZone;//时间段

    @ColumnInfo(name = "bank_no")
    public String bankNo;

    @ColumnInfo(name = "city_code")
    public String cityCode;

    @ColumnInfo(name = "staff_no")
    public String staffNo;

}
