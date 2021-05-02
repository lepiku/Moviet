package id.oktoluqman.moviet.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.oktoluqman.moviet.BuildConfig.TMDB_TOKEN
import id.oktoluqman.moviet.data.MovieDetail
import id.oktoluqman.moviet.home.TMDBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDetailViewModel: ViewModel() {
    companion object {
        private val TAG = MovieDetailViewModel::class.java.simpleName
    }

    private val movie = MutableLiveData<MovieDetail>()

    fun setMovie(id: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TMDBService::class.java)

        val call = service.getMovie(id, TMDB_TOKEN, "credits")
        call.enqueue(
            object : Callback<MovieDetail> {
                override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "onResponse: ${response.body()}")
                        movie.postValue(response.body())
                    } else {
                        Log.d(TAG, "onResponse: not success")
                        Log.d(TAG, "onResponse: $response")
                    }
                }

                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
            }
        )
    }

    fun getMovie(): LiveData<MovieDetail> = movie
}