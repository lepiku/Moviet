package id.oktoluqman.moviet.ui.home.tv.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.core.domain.model.MovieTvItem
import id.oktoluqman.moviet.core.utils.TMDBConstants
import id.oktoluqman.moviet.databinding.ActivityTvDetailBinding
import id.oktoluqman.moviet.utils.Extensions.loadImage

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
        val tv = intent.getParcelableExtra<MovieTvItem>(EXTRA_ITEM)

        tv?.let {
            viewModel.setTv(tv.id)
            viewModel.getTv().observe(this) { tv ->
                binding.imgPoster.loadImage(TMDBConstants.POSTER_BIG_URL + tv.posterPath)
                binding.tvTvDetailName.text = tv.name
                binding.tvReleaseDate.text =
                    resources.getString(
                        R.string.release_from_and_to,
                        tv.firstAirDate,
                        tv.lastAirDate
                    )
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
    }

    companion object {
        const val EXTRA_ITEM = "extra_item"
    }
}
