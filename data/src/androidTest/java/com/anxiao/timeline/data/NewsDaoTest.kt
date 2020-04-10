package com.anxiao.timeline.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.anxiao.timeline.data.database.TimeLineDatabase
import com.anxiao.timeline.data.vo.News
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsDaoTest {

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
    fun testInsertUser() {

        val news = News(0, "path", "image", "title", "time")
        database.newsDao()
            .insert(news)
            .test()
            .assertComplete()


    }

}
