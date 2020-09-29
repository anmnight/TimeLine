package com.anxiao.timeline.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anxiao.timeline.data.vo.HarvardImage

@Dao
interface HarvardImageDao {

    @Query("SELECT * FROM HarvardImage WHERE ID = :id")
    fun queryImageById(id: Int): HarvardImage

    @Query("SELECT * FROM HarvardImage")
    fun allImages(): List<HarvardImage>

    @Insert
    fun insertImages(vararg images: HarvardImage)
}