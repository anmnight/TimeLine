package com.anxiao.timeline.data

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProcesserTest {


    val processer = RegionRepository()

    @Test
    fun testProcesserGetDataNomal() {
        val result = processer.provincesList
        println(result)
        Assert.assertNotNull(result)
    }

}