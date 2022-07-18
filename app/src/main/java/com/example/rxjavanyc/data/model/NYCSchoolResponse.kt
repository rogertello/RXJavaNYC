package com.example.rxjavanyc.data.model

import com.google.gson.annotations.SerializedName

class NYCSchoolResponse {
    @SerializedName("dbn")
    var dbn: String? = null
    @SerializedName("school_name")
    var school_name: String? = null
    @SerializedName("location")
    var location: String? = null
    @SerializedName("school_email")
    var school_email: String? = null
    @SerializedName("phone_number")
    var phone_number: String? = null
}