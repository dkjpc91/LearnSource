package com.mithilakshar.learnsource.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mithilakshar.learnsource.repository.repo

class BookViewModelFactory(private val repo: repo):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookViewModel(repo) as T
    }
}