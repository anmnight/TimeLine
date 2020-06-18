package com.anxiao.timeline.views.personel

import androidx.lifecycle.*
import com.anxiao.timeline.checkResult
import com.anxiao.timeline.data.ProvinceRepo
import com.anxiao.timeline.data.cities.Province
import kotlinx.coroutines.launch

class PersonalViewModel(private val provinceRepo: ProvinceRepo) : ViewModel() {
    
    private val _queryProvinces = MutableLiveData<PersonalView>()

    val queryProvinces: LiveData<PersonalView>
        get() = _queryProvinces

    fun getProvinces() = viewModelScope.launch {


        _queryProvinces.value = PersonalView(true, listOf())

        val result = provinceRepo.provincesList()

        result.checkResult(
            onSuccess = {
                _queryProvinces.value = PersonalView(false, it)
            },
            onError = {
                _queryProvinces.value = null
            }
        )

    }


    data class PersonalView(var dialogIsShow: Boolean = false, var provinces: List<Province>)

}