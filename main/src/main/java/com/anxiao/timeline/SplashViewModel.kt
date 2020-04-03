package com.anxiao.timeline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anxiao.timeline.data.AppExecutors

class SplashViewModel : ViewModel() {

    private val executor = AppExecutors()

    private val _query = MutableLiveData<String>()

//    private var repo: BranchRepo = BranchRepo(executor, DBRegister.db().branchDao())

//    var branchLiveData: LiveData<Resource<List<Branch>>> = Transformations.switchMap(_query) { repo.loadLocalBranches() }


    fun refresh() {

        _query.value = "aaa"

    }
}

