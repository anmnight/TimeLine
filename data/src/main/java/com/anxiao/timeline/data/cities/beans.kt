package com.anxiao.timeline.data.cities

data class Area(var name:String,var code:String)

data class City(var name:String,var code:String,var areas:List<Area>)

data class Province(var name:String,var code:String,var cities:List<City>)