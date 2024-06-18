package com.novia.sanitymate.network

import com.novia.sanitymate.model.BaseApiResponse
import com.novia.sanitymate.model.PredictionEmotion
import com.novia.sanitymate.model.Recomendation
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST



interface ApiService {

    @FormUrlEncoded
    @POST("v2/predictions")
    fun postPredition(
        @Field("text") text : String
    ) : Call<BaseApiResponse<PredictionEmotion>>

    @FormUrlEncoded
    @POST("recomendation")
    fun postRecomendation(
        @Field("emotion") emotion : String
    ) : Call<BaseApiResponse<Recomendation>>
}


