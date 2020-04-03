package com.anxiao.timeline.data.database.vo;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "job_info",
        foreignKeys = @ForeignKey(entity = OrderInfo.class,
                parentColumns = "id",
                childColumns = "order_id",
                onDelete = CASCADE,
                onUpdate = CASCADE),
        indices = {
                @Index(value = ("id"), unique = true)
        })
public class JobInfo {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "order_id")
    public long orderId;

    @ColumnInfo(name = "job_info")
    public String jobType;

    @ColumnInfo(name = "position")
    public String position;

    @ColumnInfo(name = "company_name")
    public String companyName;

    @Embedded
    public Address companyAdd;

    @ColumnInfo(name = "income")
    public String income;

    @ColumnInfo(name = "other_income")
    public String otherIncome;

    @ColumnInfo(name = "family_income")
    public String familyIncome;

}
