package com.anxiao.timeline.data.file

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.anxiao.timeline.data.ApiResponse
import com.anxiao.timeline.data.database.po.Branch

interface IFileLiveData {
    fun byAssertName(
        context: Context,
        assertName: String
    ): MutableLiveData<ApiResponse<List<Branch>>>
}