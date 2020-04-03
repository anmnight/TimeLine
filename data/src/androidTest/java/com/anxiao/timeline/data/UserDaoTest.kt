package com.anxiao.timeline.data

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.anxiao.timeline.data.database.TimeLineDatabase
import com.anxiao.timeline.data.database.vo.UserInfo
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

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

        val user = UserInfo()
        user.name = "anxiao"
        user.sex = 1
        user.birthday = 1999L
        user.userId = "uid001"

        database.userDao()
            .insert(user)
            .test()
            .assertComplete()
    }

}
