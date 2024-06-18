package com.novia.sanitymate.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.novia.sanitymate.model.BaseApiResponse
import com.novia.sanitymate.model.PredictionEmotion
import com.novia.sanitymate.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PredictionViewModel : ViewModel() {

    private val apiService = ApiConfig.getApiService()

    private val _recommendation = MutableLiveData<PredictionEmotion?>()
    val recommendation: LiveData<PredictionEmotion?> = _recommendation

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun postPrediction(text: String) {
        apiService.postPredition(text).enqueue(object :
            Callback<BaseApiResponse<PredictionEmotion>> {
            override fun onResponse(call: Call<BaseApiResponse<PredictionEmotion>>, response: Response<BaseApiResponse<PredictionEmotion>>) {
                if (response.isSuccessful) {
                    _recommendation.value = response.body()?.data
                } else {
                    _error.value = response.message()
                }
            }

            override fun onFailure(call: Call<BaseApiResponse<PredictionEmotion>>, t: Throwable) {
                _error.value = t.message
            }
        })
    }
}
