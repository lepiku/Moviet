package id.oktoluqman.moviet.ui.home.movie.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.core.domain.model.MovieTvItem
import id.oktoluqman.moviet.core.utils.TMDBConstants
import id.oktoluqman.moviet.databinding.ActivityMovieDetailBinding
import id.oktoluqman.moviet.core.utils.Extensions.loadImage

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpViewModel()

        binding.btnFavorite.setOnClickListener {
            viewModel.toggleFavorite()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun setUpViewModel() {
        val movie = intent.getParcelableExtra<MovieTvItem>(EXTRA_ITEM)

        movie?.let {
            viewModel.setMovie(movie)
            viewModel.getMovie().observe(this) { movie ->
                binding.imgPoster.loadImage(TMDBConstants.POSTER_BIG_URL + movie.posterPath)
                binding.tvMovieDetailTitle.text = movie.title
                binding.tvReleaseDate.text = movie.releaseDate
                binding.tvGenre.text = movie.genres.joinToString { it.name }
                binding.tvVoteAverage.text = movie.voteAverage.toString()
                binding.tvDirector.text = movie.credits.crew.find { it.job == "Director" }?.name
                binding.tvOverview.text = movie.overview
                binding.tvStatus.text = movie.status
                binding.tvRevenue.text = movie.revenue.toString()
            }

            viewModel.favorite.observe(this) { status ->
                if (status)
                    binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                else
                    binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }

    companion object {
        const val EXTRA_ITEM = "extra_item"
    }
}
