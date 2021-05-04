package id.oktoluqman.moviet.movie.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.oktoluqman.moviet.BuildConfig.TMDB_TOKEN
import id.oktoluqman.moviet.data.DiscoverQuery
import id.oktoluqman.moviet.data.MovieItem
import id.oktoluqman.moviet.services.TMDBService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieListViewModel : ViewModel() {
    companion object {
        private val TAG = MovieListViewModel::class.java.simpleName
    }

    private val movieList = MutableLiveData<List<MovieItem>>()

    fun queryItemList() {
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(TMDBService::class.java)

        val call = service.discoverMovies(TMDB_TOKEN)
        call.enqueue(
            object : Callback<DiscoverQuery<MovieItem>> {
                override fun onResponse(
                    call: Call<DiscoverQuery<MovieItem>>,
                    response: Response<DiscoverQuery<MovieItem>>,
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "onResponse: ${response.body()}")
                        movieList.postValue(response.body()?.results)
                    } else {
                        Log.d(TAG, "onResponse: not success")
                    }
                }

                override fun onFailure(call: Call<DiscoverQuery<MovieItem>>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }

            }
        )
    }

    fun getItemList(): LiveData<List<MovieItem>> = movieList
}