package com.anxiao.timeline.data

import com.anxiao.timeline.data.network.Server
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetUserInfoTest {

    //远端服务
    private val services = Server.services()

    @Test
    fun testNews() {
        val result = services.getNews().blockingGet()
        Assert.assertEquals(result.code, 200)
    }


}