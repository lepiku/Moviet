package id.oktoluqman.moviet.ui.tv.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.databinding.ActivityTvDetailBinding
import id.oktoluqman.moviet.utils.TMDBConstants

@AndroidEntryPoint
class TvDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvDetailBinding
    private val viewModel by viewModels<TvDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.tv_show_detail)
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
        val tvId = intent.getIntExtra(EXTRA_ID, 0)

        viewModel.setTv(tvId)
        viewModel.getTv().observe(this) { tv ->
            Glide.with(this)
                .load(TMDBConstants.POSTER_BIG_URL + tv.posterPath)
                .placeholder(R.drawable.ic_baseline_refresh_24).centerCrop()
                .error(R.drawable.ic_baseline_broken_image_24).centerCrop()
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

        viewModel.favorite.observe(this) { status ->
            if (status)
                binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            else
                binding.btnFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}
