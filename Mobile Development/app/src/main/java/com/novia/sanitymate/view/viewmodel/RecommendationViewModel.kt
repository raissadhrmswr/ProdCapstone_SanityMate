package com.novia.sanitymate.view.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novia.sanitymate.model.BaseApiResponse
import com.novia.sanitymate.model.Recomendation
import com.novia.sanitymate.network.ApiConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationViewModel : ViewModel() {

    private val apiService = ApiConfig.getApiService()

    val recommendationResponse: MutableLiveData<BaseApiResponse<Recomendation>> = MutableLiveData()
    val recommendationError: MutableLiveData<String> = MutableLiveData()
    val progress: MutableLiveData<Int> = MutableLiveData()

    fun fetchRecommendation(emotion: String) {
        progress.value = 0
        viewModelScope.launch {
            while (progress.value!! < 100) {
                delay(100)
                progress.value = progress.value!! + 5
            }
        }

        apiService.postRecomendation(emotion).enqueue(object : Callback<BaseApiResponse<Recomendation>> {
            override fun onResponse(
                call: Call<BaseApiResponse<Recomendation>>,
                response: Response<BaseApiResponse<Recomendation>>
            ) {
                if (response.isSuccessful) {
                    recommendationResponse.value = response.body()
                    progress.value = 100
                } else {
                    recommendationError.value = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<BaseApiResponse<Recomendation>>, t: Throwable) {
                recommendationError.value = "Network Error: ${t.message}"
            }
        })
    }
}

