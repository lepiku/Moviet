package id.oktoluqman.moviet.ui.favorite.movie.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.databinding.ActivityMovieDetailBinding
import id.oktoluqman.moviet.utils.TMDBConstants

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(EXTRA_ID, 0)

        viewModel.setMovie(movieId)
        viewModel.getMovie().observe(this) { movie ->
            Glide.with(this)
                .load(TMDBConstants.POSTER_BIG_URL + movie.movieDetail.posterPath)
                .placeholder(R.drawable.ic_baseline_refresh_24).centerCrop()
                .error(R.drawable.ic_baseline_broken_image_24).centerCrop()
                .into(binding.imgPoster)
            binding.tvMovieDetailTitle.text = movie.movieDetail.title
            binding.tvReleaseDate.text = movie.movieDetail.releaseDate
            binding.tvGenre.text = movie.genres.joinToString { it.name }
            binding.tvVoteAverage.text = movie.movieDetail.voteAverage.toString()
            binding.tvDirector.text = movie.crews.find { it.job == "Director" }?.name
            binding.tvOverview.text = movie.movieDetail.overview
            binding.tvStatus.text = movie.movieDetail.status
            binding.tvRevenue.text = movie.movieDetail.revenue.toString()
        }

        supportActionBar?.title = getString(R.string.movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
