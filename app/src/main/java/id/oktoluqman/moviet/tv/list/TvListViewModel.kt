package id.oktoluqman.moviet.tv.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.oktoluqman.moviet.BuildConfig.TMDB_TOKEN
import id.oktoluqman.moviet.data.DiscoverQuery
import id.oktoluqman.moviet.data.TvItem
import id.oktoluqman.moviet.services.TMDBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TvListViewModel : ViewModel() {
    companion object {
        private val TAG = TvListViewModel::class.java.simpleName
    }

    private val movieList = MutableLiveData<List<TvItem>>()

    fun queryItemList() {
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TMDBService::class.java)

        val call = service.discoverTv(TMDB_TOKEN)

        call.enqueue(
            object : Callback<DiscoverQuery<TvItem>> {
                override fun onResponse(
                    call: Call<DiscoverQuery<TvItem>>,
                    response: Response<DiscoverQuery<TvItem>>,
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "onResponse: ${response.body()}")
                        movieList.postValue(response.body()?.results)
                    } else {
                        Log.d(TAG, "onResponse: not success")
                    }
                }

                override fun onFailure(call: Call<DiscoverQuery<TvItem>>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }

            }
        )
    }

    fun getItemList(): LiveData<List<TvItem>> = movieList
}