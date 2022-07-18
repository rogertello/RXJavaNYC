package com.example.rxjavanyc.repository


import com.example.rxjavanyc.data.api.Network
import com.example.rxjavanyc.data.model.NYCSchoolResponse
import com.example.rxjavanyc.repository.state.SATResponse
import com.example.rxjavanyc.repository.state.SchoolResponse
import com.example.rxjavanyc.repository.state.UIState
import io.reactivex.rxjava3.core.Single


class RepositoryImpl(val network: Network) : Repository {
   // private val network: Network
//    override fun schoolList: Single<UIState>
//        get() = network.sERVICE?.getListSchools()!!
//                  .map { nycSchoolResponses ->
//                      SchoolResponse(nycSchoolResponses)
//                  }



    override fun schoolList(): Single<UIState> {
       return network.SERVICE?.getListSchools()!!
            .map { nycSchoolResponses ->
                var schoolResponse: SchoolResponse = SchoolResponse(nycSchoolResponses)

                schoolResponse
            }
    }




    override fun getSchoolDetails(dbn: String?): Single<UIState> {
        return network.SERVICE?.getListSAT()!!
            .map { nycSchoolSats ->
                lateinit var satSchool: SATResponse
                for (sat in nycSchoolSats) {
                    if (sat.dbn.equals(dbn))
                    {
                        satSchool =SATResponse(sat)
                    }
                }
                 satSchool
            }
    }
}