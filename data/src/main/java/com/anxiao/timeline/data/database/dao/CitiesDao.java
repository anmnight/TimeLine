package com.anxiao.timeline.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.anxiao.timeline.data.database.po.City;

import java.util.List;

@Dao
public interface CitiesDao {

    @Insert
    public void insert(List<City> cities);

    @Query("SELECT * FROM cities")
    public List<City> findAll();

    @Query("SELECT * FROM CITIES WHERE code LIKE :cityCode LIMIT 1")
    public City findByCode(String cityCode);

    @Query("SELECT * FROM CITIES WHERE name LIKE :cityName LIMIT 1")
    public City findCitisByName(String cityName);

    @Query("DELETE FROM CITIES")
    public void clear();

}
