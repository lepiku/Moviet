package id.oktoluqman.moviet.tv.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.databinding.ActivityTvDetailBinding

class TvDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityTvDetailBinding
    private val viewModel by viewModels<TvDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvId = intent.getIntExtra(EXTRA_ID, 0)
        viewModel.setTv(tvId)
        viewModel.getTv().observe(this) { tv ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185${tv.posterPath}")
                .into(binding.imgPoster)
            binding.tvTvDetailName.text = tv.name
            binding.tvReleaseDate.text =
                resources.getString(R.string.release_from_and_to, tv.firstAirDate, tv.lastAirDate)
            binding.tvGenre.text = tv.genres.joinToString { it.name }
            binding.tvVoteAverage.text = tv.voteAverage.toString()
            if (tv.createdBy.isNotEmpty())
                binding.tvCreator.text = tv.createdBy.joinToString { it.name }
            else {
                binding.tvCreatorLabel.visibility = View.GONE
                binding.tvCreator.visibility = View.GONE
            }
            binding.tvOverview.text = tv.overview
            binding.tvStatus.text = tv.status
        }

        supportActionBar?.title = "TV Show Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}