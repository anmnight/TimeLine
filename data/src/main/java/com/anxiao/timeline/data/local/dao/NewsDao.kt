package com.anxiao.timeline.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anxiao.timeline.data.vo.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<News>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News)

    @Query("SELECT * FROM NEWS")
    fun find(): List<News>

}