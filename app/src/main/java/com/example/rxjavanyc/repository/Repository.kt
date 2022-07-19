package com.example.rxjavanyc.repository


import com.example.rxjavanyc.repository.state.UIState
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun schoolList(): Single<UIState>
    fun getSchoolDetails(dbn: String?): Single<UIState>
}