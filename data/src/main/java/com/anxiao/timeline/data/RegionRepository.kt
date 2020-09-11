package com.anxiao.timeline.data

import com.anxiao.core.exception.Failure
import com.anxiao.core.functional.Either
import com.anxiao.timeline.data.cities.City
import com.anxiao.timeline.data.cities.Province
import com.anxiao.timeline.data.network.RegionService
import org.jsoup.Connection
import org.jsoup.nodes.Document
import java.util.*

interface RegionRepository {

    fun provinces(): Either<Failure, List<Province>>


    class Network(private val service: RegionService) : RegionRepository {
        override fun provinces(): Either<Failure, List<Province>> {

            return request(service.provinces()) { document ->
                parse(document)
            }
        }


        private fun <R> request(
            connection: Connection,
            transform: (Document) -> R,
        ): Either<Failure, R> {
            return try {
                when (connection.execute().statusCode()) {
                    in 200..299 -> Either.Right(transform(connection.maxBodySize(0).get()))
                    else -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }


        private fun parse(doc: Document): List<Province> {


            val elements = doc.getElementsByClass("xl7024197")
            val stringList = elements.eachText()
            val stringName: MutableList<String> = ArrayList()
            val stringCode: MutableList<String> = ArrayList()
            for (i in stringList.indices) {
                if (i % 2 == 0)
                // 地区代码
                    stringCode.add(stringList[i])
                else
                // 地区名字
                    stringName.add(stringList[i])

            }
            stringName.add("end")
            stringCode.add("000000")
            val provinceList: MutableList<Province> = ArrayList()
            val provinceIndexList: MutableList<Int> = ArrayList()
            for (i in stringCode.indices) {
                val code = stringCode[i]
                if (code.endsWith("0000")) {
                    provinceIndexList.add(i)
                }
            }
            for (i in 0 until provinceIndexList.size - 1) {
                // 每个省份所在的下标，省份所在的所有省市区数据区间为[index,index-1]
                val index = provinceIndexList[i]
                val nextIndex = provinceIndexList[i + 1]
                val name: MutableList<String> = ArrayList()
                val code: MutableList<String> = ArrayList()

                // 每个省份下面的所有数据集合
                for (j in index until nextIndex) {
                    name.add(stringName[j])
                    code.add(stringCode[j])
                }

                // 获取省份的下标
                val proIndex = getProIndex(name, code)

                // 设置省份数据
                val cities: MutableList<com.anxiao.timeline.data.cities.City> = ArrayList()
                val province = Province(name[proIndex], code[proIndex], cities)

                // 获取城市前四位code
                val cityList =
                    getPre4CodeList(name, code)

                // 根据每个前四位code赖取出集合中所有的相同前四位数据，然后根据是否是00结尾的分类
                for (j in cityList.indices) {
                    // code前四位
                    val codeStub = cityList[j]
                    val cityCodeList: MutableList<String> = ArrayList()
                    val cityNameList: MutableList<String> = ArrayList()

                    // 获取前四位每组所有的城市数据
                    for (k in name.indices) {
                        val tempName = name[k]
                        val tempCode = code[k]
                        if (tempCode.startsWith(codeStub) && !tempCode.endsWith("0000")) {
                            cityCodeList.add(tempCode)
                            cityNameList.add(tempName)
                        }
                    }

                    // 判断是否是省直辖县级行政单位，如果一组数据中有00结尾的，则是正常的市级城市数据
                    val `is` =
                        isContainer(cityCodeList, cityNameList)
                    if (`is`) {
                        val areas: MutableList<com.anxiao.timeline.data.cities.Area> = ArrayList()
                        val city =
                            com.anxiao.timeline.data.cities.City("省直辖县级行政单位", province.code, areas)
                        // 省直辖县级行政单位
                        for (k in cityCodeList.indices) {
                            val tempName = cityNameList[k]
                            val tempCode = cityCodeList[k]
                            val area =
                                com.anxiao.timeline.data.cities.Area(tempName, tempCode)
                            areas.add(area)
                        }
                        city.areas = areas
                        cities.add(city)
                    } else {
                        // 正常的市级城市数据
                        val areas: MutableList<com.anxiao.timeline.data.cities.Area> = ArrayList()
                        val city = City("", "", areas)
                        for (k in cityCodeList.indices) {
                            val tempName = cityNameList[k]
                            val tempCode = cityCodeList[k]
                            // 市
                            if (tempCode.endsWith("00")) {
                                city.name = tempName
                                city.code = tempCode
                            } else {
                                // 区
                                val area =
                                    com.anxiao.timeline.data.cities.Area(tempName, tempCode)
                                areas.add(area)
                            }
                        }
                        city.areas = areas
                        cities.add(city)
                    }
                }
                provinceList.add(province)
            }

            return provinceList


        }

        private fun isContainer(
            cityCodeList: List<String>,
            cityNameList: List<String>
        ): Boolean {
            var isContainer = true
            // 判断是否是省直辖县级行政单位，如果一组数据中有00结尾的，则是正常的市级城市数据
            for (k in cityCodeList.indices) {
                val tempName = cityNameList[k]
                val tempCode = cityCodeList[k]
                if (tempCode.endsWith("00")) {
                    isContainer = false
                    break
                }
            }
            return isContainer
        }

        private fun getPre4CodeList(
            name: List<String>,
            code: List<String>
        ): List<String> {
            val citySet: MutableSet<String> =
                HashSet()
            for (j in name.indices) {
                val tempCode = code[j].substring(0, 4)
                citySet.add(tempCode)
            }
            val iterator: Iterator<String> = citySet.iterator()
            val cityList: MutableList<String> =
                ArrayList()
            while (iterator.hasNext()) {
                val stub = iterator.next()
                cityList.add(stub)
            }
            return cityList
        }

        private fun getProIndex(
            name: List<String>,
            code: List<String>
        ): Int {
            var proIndex = 0
            for (j in name.indices) {
                val tempName = name[j]
                val tempCode = code[j]
                if (tempCode.endsWith("0000")) {
                    proIndex = j
                    break
                }
            }
            return proIndex
        }
    }
}