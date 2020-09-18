package com.anxiao.timeline.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.anxiao.timeline.data.db.TimeLineDatabase
import com.anxiao.timeline.data.vo.HarvardImage
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HarvardImageDaoTest {

    private val harvardImageDao by lazy {
        database.harvardImageDao()
    }

    private lateinit var database: TimeLineDatabase

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            TimeLineDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDb() {
        database.close()
    }


    @Test
    fun testInsert() {
        HarvardImage.empty()
            .run {
                harvardImageDao.insertImages(this)
                val list = harvardImageDao.allImages()
                Assert.assertEquals(list.size, 1)
            }
    }

    @Test
    fun testFindById() {
        HarvardImage.empty()
            .run {
                harvardImageDao.insertImages(this)
                val image = harvardImageDao.queryImageById(0)
                Assert.assertEquals(this, image)
            }
    }


}
