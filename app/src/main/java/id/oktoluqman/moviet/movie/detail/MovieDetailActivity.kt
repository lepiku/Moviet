package id.oktoluqman.moviet.movie.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.oktoluqman.moviet.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(EXTRA_ID, 0)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MovieDetailViewModel::class.java]
        viewModel.setMovie(movieId)
        viewModel.getMovie().observe(this) { movie ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185${movie.posterPath}")
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

        supportActionBar?.title = "Movie Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}