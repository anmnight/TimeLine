package com.anxiao.timeline.data

import com.anxiao.timeline.data.network.Server
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.single
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsRemoteApiTest {

    @Test
    fun testGetNewsApi() {
        val result = runBlocking {
            Server.services().getNews()
        }
        Assert.assertEquals(result.code, 200)
    }


}