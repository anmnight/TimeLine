package com.anxiao.timeline.data.vo;

import androidx.room.ColumnInfo;

public class Address {

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "province")
    public String province;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "district")
    public String district;

    @ColumnInfo(name = "street")
    public String street;

    @ColumnInfo(name = "postCode")
    public String postalCode;

}
