package com.anxiao.timeline.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.anxiao.timeline.data.database.TimeLineDatabase
import com.anxiao.timeline.data.network.RemoteService
import com.anxiao.timeline.data.network.Result
import com.anxiao.timeline.data.network.Server
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NewsReposTest {

    private lateinit var database: TimeLineDatabase
    private lateinit var api: RemoteService
    private lateinit var newsRepo: NewsRepo

    //todo mockio 正例 反例 及特殊情况


    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            TimeLineDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        api = Server.services()

        newsRepo = NewsRepo(api, database.newsDao())

    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testRequestNews() = runBlocking {
        val result = newsRepo.requestNews()
        Assert.assertTrue(result is Result.Success)
    }






}
