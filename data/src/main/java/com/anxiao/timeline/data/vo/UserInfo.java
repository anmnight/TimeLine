package com.anxiao.timeline.data.vo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * UserInfo
 */
@Entity(
        tableName = "user_info",
        indices = {
                @Index(value = ("userId"), unique = true)
        }
)
public class UserInfo {

    @NonNull
    @PrimaryKey
    public String userId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "sex")
    public int sex;    //性别 0 男 1女

    @ColumnInfo(name = "birthday")
    public long birthday;

}
