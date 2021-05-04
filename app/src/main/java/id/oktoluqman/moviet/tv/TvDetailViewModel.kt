package id.oktoluqman.moviet.tv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.oktoluqman.moviet.BuildConfig
import id.oktoluqman.moviet.data.TvDetail
import id.oktoluqman.moviet.home.TMDBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TvDetailViewModel : ViewModel() {
    companion object {
        private val TAG = TvDetailViewModel::class.java.simpleName
    }

    private val tv = MutableLiveData<TvDetail>()

    fun setTv(id: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TMDBService::class.java)

        val call = service.getTv(id, BuildConfig.TMDB_TOKEN, "credits")
        call.enqueue(
            object : Callback<TvDetail> {
                override fun onResponse(call: Call<TvDetail>, response: Response<TvDetail>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "onResponse: ${response.body()}")
                        tv.postValue(response.body())
                    } else {
                        Log.d(TAG, "onResponse: not success")
                        Log.d(TAG, "onResponse: $response")
                    }
                }

                override fun onFailure(call: Call<TvDetail>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            }
        )
    }

    fun getTv(): LiveData<TvDetail> = tv
}