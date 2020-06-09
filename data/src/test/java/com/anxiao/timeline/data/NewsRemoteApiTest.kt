package com.anxiao.timeline.data

import com.anxiao.timeline.data.network.Server
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsRemoteApiTest {


    @Test
    fun testGetNewsApi() = runBlocking {
        val result = Server.services().getNews()
        Assert.assertEquals(result.code, 200)
    }




}