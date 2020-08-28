package com.anxiao.timeline.data.local.po

import androidx.room.*

@Entity(
    tableName = "cities",
    indices = [Index(value = ["code"], unique = true)]
)
data class City(

    @PrimaryKey
    val code: String,

    @ColumnInfo(name = "parent_code")
    val parentCode: String,

    @ColumnInfo
    val name: String,

    @ColumnInfo(name = "res_id_name")
    val resIdName: String


){
    companion object{
        val DEFAULT_CODE = "0000"
    }
}
