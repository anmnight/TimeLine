package com.anxiao.timeline.data.local.po;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "branches")
public class Branch {

    @PrimaryKey
    @ColumnInfo(name = "branch_no")
    public String branchNo;

    @ColumnInfo(name = "branch_name")
    public String branchName;

    @ColumnInfo(name = "city_code")
    public String cityCode;

    @ColumnInfo(name = "address")
    public String address;

}
