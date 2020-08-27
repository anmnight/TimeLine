package com.anxiao.timeline.views.personel

import androidx.lifecycle.*
import com.anxiao.timeline.data.RegionRepository
import com.anxiao.timeline.data.cities.Province

class PersonalViewModel(private val regionRepository: RegionRepository) : ViewModel() {
    
    private val _queryProvinces = MutableLiveData<PersonalView>()

    val queryProvinces: LiveData<PersonalView>
        get() = _queryProvinces




    data class PersonalView(var dialogIsShow: Boolean = false, var provinces: List<Province>)

}