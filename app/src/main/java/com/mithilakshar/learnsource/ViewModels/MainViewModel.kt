package com.mithilakshar.learnsource.ViewModels

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.mithilakshar.learnsource.repository.MainRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainViewModel(val repo: MainRepo) : ViewModel() {
    val homeLiveData get() = repo.homeLiveData

    fun getHomeData() {
        CoroutineScope(Dispatchers.IO).launch{
            repo.getHomeData()
        }
    }


}