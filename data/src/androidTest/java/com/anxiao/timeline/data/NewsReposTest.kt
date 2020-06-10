package com.anxiao.timeline.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.anxiao.timeline.data.database.TimeLineDatabase
import com.anxiao.timeline.data.database.dao.NewsDao
import com.anxiao.timeline.data.network.RemoteService
import com.anxiao.timeline.data.network.RestResponse
import com.anxiao.timeline.data.network.Result
import com.anxiao.timeline.data.network.Server
import com.anxiao.timeline.data.vo.News
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*


@RunWith(AndroidJUnit4::class)
class NewsReposTest {

    private var dao: NewsDao = mock(NewsDao::class.java)
    private var api: RemoteService = mock(RemoteService::class.java)

    private var newsRepo: NewsRepo = NewsRepo(api, dao)

    //todo mockio 正例 反例 及特殊情况


    @Test
    fun testRequestNews() = runBlocking {

        val listNews = arrayListOf<News>()

        `when`(api.getNews()).thenReturn(RestResponse(200, "success", listNews))

        val result = newsRepo.requestNews()

        Assert.assertTrue(result is Result.Success)
    }


}
