package com.anxiao.timeline.data.mock

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.anxiao.timeline.data.ApiResponse
import com.anxiao.timeline.data.ApiSuccessResponse
import com.anxiao.timeline.data.database.po.Branch
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.lang.Exception

class AssertLiveData : IFileLiveData {

    companion object {
        fun instance() = AssertLiveData()
    }

    override fun byAssertName(
        context: Context,
        assertName: String
    ): MutableLiveData<ApiResponse<List<Branch>>> {
        val fileLiveData = object : MutableLiveData<ApiResponse<List<Branch>>>() {}
        return try {
            fileLiveData.value = ApiSuccessResponse(getObject(context, assertName))
            fileLiveData
        } catch (e: Exception) {
            fileLiveData.value = ApiResponse.create(Throwable(e.message))
            fileLiveData
        }
    }


    private fun getObject(context: Context, assertName: String): List<Branch> {
        val stream = context.assets.open(assertName)
        val bos = ByteArrayOutputStream()

        var read = -1
        while ({ read = stream.read();read }() != -1) {
            bos.write(read)
        }

        val json = bos.toString()
        bos.flush()
        bos.close()
        stream.close()
        //todo 增加 List 判断

        return Gson().fromJson(json, object : TypeToken<List<Branch>>() {}.type)
    }
}