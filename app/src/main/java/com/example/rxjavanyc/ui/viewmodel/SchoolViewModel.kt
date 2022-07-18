package com.example.rxjavanyc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.rxjavanyc.repository.Repository
import com.example.rxjavanyc.repository.state.SATResponse
import com.example.rxjavanyc.repository.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(var repository: Repository) : ViewModel() {
    private val _schoolState: MutableLiveData<UIState> = MutableLiveData<UIState>()
    val schoolState: LiveData<UIState>
        get() = _schoolState

    init {
        init()
    }

    fun init() {
        repository.schoolList()
            .subscribeOn(
                Schedulers.io()
            )
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe(
                object : SingleObserver<UIState> {
                    override fun onSubscribe(d: Disposable) {
                        // dispose any composable...
                    }

                    override fun onSuccess(uiState: UIState) {
                        _schoolState.value=uiState
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                }
            )
    }

    fun getSatDetails(dbn: String?) {
        repository.getSchoolDetails(dbn)
            .subscribeOn(
                Schedulers.io()
            )
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe(
                object : SingleObserver<UIState> {
                    override fun onSubscribe(d: Disposable) {
                        // dispose any composable...
                    }

                    override fun onSuccess(uiState: UIState) {
                        Log.d(
                            "TAG",
                            "onSuccess: " + (uiState as SATResponse).data.num_of_sat_test_takers
                        )
                        _schoolState.value= uiState
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                }
            )
    }


}