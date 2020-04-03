package com.anxiao.timeline.data.database.vo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * UserInfo
 */
@Entity(
        tableName = "user_info",
        foreignKeys =
        @ForeignKey(entity = OrderInfo.class,
                parentColumns = "id",
                childColumns = "order_id",
                onDelete = CASCADE,
                onUpdate = CASCADE),
        indices = {
                @Index(value = ("id"), unique = true)
        }
)
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "order_id")
    public long orderId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "card_name")
    public String printName;

    @ColumnInfo(name = "sex")
    public int sex;    //性别 0 男 1女

    @ColumnInfo(name = "nationality")
    public String nationality;

    @ColumnInfo(name = "id_type")
    public String idType;

    @ColumnInfo(name = "id_num")
    public String idNum;

    @ColumnInfo(name = "id_organs_add")
    public String idOrgansAdd;

    @ColumnInfo(name = "id_start_time")
    public long idStartTime;

    @ColumnInfo(name = "id_end_time")
    public long idEndTime;

    @ColumnInfo(name = "birthday")
    public long birthday;

    @ColumnInfo(name = "birth_country")
    public String birthCountry;

    @ColumnInfo(name = "birth_province")
    public String birthProvince;

}
