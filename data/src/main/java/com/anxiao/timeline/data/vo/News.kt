package com.anxiao.timeline.data.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo
    var path: String,
    @ColumnInfo
    var image: String,
    @ColumnInfo
    var title: String,
    @ColumnInfo
    var passtime: String

)
