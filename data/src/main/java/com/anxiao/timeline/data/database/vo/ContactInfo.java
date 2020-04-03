package com.anxiao.timeline.data.database.vo;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "contact_info",
        foreignKeys = @ForeignKey(entity = OrderInfo.class,
                parentColumns = "id",
                childColumns = "order_id",
                onDelete = CASCADE,
                onUpdate = CASCADE),
        indices = {
                @Index(value = ("id"), unique = true)
        })
public class ContactInfo {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "order_id")
    public long orderId;

    @ColumnInfo
    public String emailAddress;

    @ColumnInfo(name = "mouth_langage")
    public String priorityLanguage;

    @Embedded(prefix = "home_")
    public Address homeAdd;

    @Embedded(prefix = "post_")
    public Address postAdd;
}
