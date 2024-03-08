package com.mithilakshar.learnsource.ViewModels


import androidx.lifecycle.ViewModel
import com.mithilakshar.learnsource.repository.repo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(val repo: repo): ViewModel() {

    val downloadLiveData get() = repo.downloadLiveData

    fun downloadFile(url: String, fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.downloadpdf(
                url = url,
                fileName = fileName
            )
        }
    }
}