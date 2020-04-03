package com.anxiao.timeline.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.anxiao.timeline.data.database.po.Branch;

import java.util.List;


@Dao
public interface BranchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(List<Branch> branches);

    @Query("SELECT * FROM BRANCHES")
    public LiveData<List<Branch>> findAll();


    @Query("SELECT * FROM BRANCHES WHERE city_code LIKE :cityCode")
    public LiveData<List<Branch>> findByCity(String cityCode);

    @Query("SELECT * FROM BRANCHES WHERE branch_no LIKE :branchNo LIMIT 1")
    public LiveData<Branch> findByNo(String branchNo);
}
