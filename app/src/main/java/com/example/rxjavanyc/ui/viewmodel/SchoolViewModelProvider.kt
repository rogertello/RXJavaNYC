package com.example.rxjavanyc.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rxjavanyc.repository.Repository
import javax.inject.Inject


class SchoolViewModelProvider @Inject constructor(val repository: Repository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SchoolViewModel(repository) as T
    }

}