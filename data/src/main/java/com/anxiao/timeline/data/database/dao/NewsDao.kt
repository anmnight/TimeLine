package com.anxiao.timeline.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anxiao.timeline.data.vo.News
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: List<News>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News): Completable

    @Query("SELECT * FROM NEWS")
    fun find(): Single<List<News>>

}