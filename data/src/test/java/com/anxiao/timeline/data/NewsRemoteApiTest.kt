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

    @Test
    fun test() {

        val str1 = "asdasd"
        val str2 = "qweqweq"
        val str3 = "12312as"
        val str4 = "zxczxc"

        val imageList = arrayListOf<String>()

        imageList.add(str1)
        imageList.add(str2)
        imageList.add(str3)
        imageList.add(str4)

        fun createStr(imageList: ArrayList<String>): String {
            var result = "["
            for (str in imageList) {
                result += "\"$str\","
            }
            result = result.substring(0, result.length - 1)
            result += "]"
            return result
        }

        val result = createStr(imageList)

        println(result)

    }

    //记录能够到达的最远距离
    private var maxPosition = 0
    private var steps = 0
    //记录上一步 step 跳的最远距离也就是边界
    private var end=0

//    fun jump(nums: IntArray): Int {
//
//        for ( i in nums){
//
//        }
//
//        //这里有个小细节，因为是起跳的时候就 + 1 了，如果最后一次跳跃刚好到达了最后一个位置，那么遍历到最后一个位置的时候就会再次起跳，这是不允许的，因此不能遍历最后一个位置
//        for i: = 0;i < len(nums) - 1;i++{
//            //更新最大距离
//            maxPosition = int(Math.max(Math.float64(maxPosition), float64(nums[i] + i)))
//            /*
//               我们什么时候需要步数 + 1？
//               当到达上一步的最远距离的时候，那么意味着我们需要进行一次新的起跳，那么步数 + 1
//               并且更新最远距离
//            */
//            ////第一次起跳 或 到达跳跃的边界
//            if i == end {
//                end = maxPosition
//                steps++
//            }
//        }
//        return steps
//    }


}