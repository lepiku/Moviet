package id.oktoluqman.moviet.ui.movie.detail

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
                .load(TMDBConstants.POSTER_BIG_URL + movie.posterPath)
                .into(binding.imgPoster)
            binding.tvMovieDetailTitle.text = movie.title
            binding.tvReleaseDate.text = movie.releaseDate
            binding.tvGenre.text = movie.genres.joinToString { it.name }
            binding.tvVoteAverage.text = movie.voteAverage.toString()
            binding.tvDirector.text = movie.credits.crew.find { it.job == "Director" }?.name
            binding.tvOverview.text = movie.overview
            binding.tvStatus.text = movie.status
            binding.tvRevenue.text = movie.revenue.toString()
        }

        supportActionBar?.title = getString(R.string.movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}