package com.anxiao.timeline.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.anxiao.timeline.data.local.TimeLineDatabase
import com.anxiao.timeline.data.vo.News
import kotlinx.coroutines.runBlocking
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
    fun testInsertUser() = runBlocking {
        val news = News(0, "path", "image", "title", "time")
        database.newsDao().insert(news)
        val result = database.newsDao().find()
        Assert.assertEquals(result.size, 1)
    }


}
