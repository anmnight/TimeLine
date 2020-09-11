package com.anxiao.timeline.data.local

import android.content.Context
import com.anxiao.timeline.data.local.po.Branch
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream

fun getObject(context: Context, assertName: String): List<Branch> {
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