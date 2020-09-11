package com.anxiao.timeline.data.network

import org.jsoup.Connection
import org.jsoup.Jsoup

class RegionService {


    // 2018年11月中华人民共和国县以上行政区划代码网页
    fun provinces(): Connection =
        Jsoup.connect("http://www.mca.gov.cn/article/sj/xzqh/2018/201804-12/20181101021046.html")





}