package com.example.rxjavanyc.data.api

import com.example.rxjavanyc.data.model.NYCSATResponse
import com.example.rxjavanyc.data.model.NYCSchoolResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYCSchoolsInterface {
    @GET(ENDPOINT_SCHOOL)
    fun getListSchools(): Single<List<NYCSchoolResponse>>

    @GET(ENDPOINT_SAT)
    fun getListSAT(): Single<List<NYCSATResponse>>
}