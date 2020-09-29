package com.anxiao.timeline.data

import com.anxiao.timeline.data.network.HarvardService
import com.anxiao.timeline.data.network.Server
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HarvardServiveTest {

    private val _index = 1
    private val _imageId = 75614

    private val harvardService by lazy {
        HarvardService(Server().retrofit())
    }

    @Test(timeout = 6000)
    fun `test get images api is ok`() = runBlocking {
        val resp = harvardService.getHarvardImages(_index)
        Assert.assertNotEquals(resp.info, null)
    }

    @Test(timeout = 6000)
    fun `test get image details is ok`() = kotlin.run {
        val resp = harvardService.getHarvardImageDetails(_imageId).execute()
        Assert.assertEquals(resp.code(), 200)
    }

}