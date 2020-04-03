package com.anxiao.timeline.data.database.vo;

import androidx.room.ColumnInfo;

/**
 * @author: anxiao
 * @version: V1.0
 * @project: MatmMobile
 * @package: com.chinasofti.matmmobile.data.model
 * @description: description
 * @date: 2019-12-31
 * @time: 11:18
 */
public class Address {

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "province")
    public String province;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "area")
    public String area;

    @ColumnInfo(name = "street")
    public String street;

    @ColumnInfo(name = "postCode")
    public String postalCode;

}
