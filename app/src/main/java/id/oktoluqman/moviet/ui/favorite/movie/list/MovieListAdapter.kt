package id.oktoluqman.moviet.ui.favorite.movie.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.databinding.ItemBinding
import id.oktoluqman.moviet.utils.Extensions.loadImage
import id.oktoluqman.moviet.utils.TMDBConstants

class MovieListAdapter(val onClick: (id: Int) -> Unit) :
    PagingDataAdapter<MovieItemEntity, MovieListAdapter.ListViewHolder>(diffCallback) {

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBinding.bind(view)

        fun bind(movieItem: MovieItemEntity) {
            binding.imgThumbnail.loadImage(TMDBConstants.POSTER_SMALL_URL + movieItem.posterPath)
            binding.tvName.text = movieItem.title
            binding.tvOverview.text = movieItem.overview
            binding.card.setOnClickListener { onClick(movieItem.movieId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null)
            holder.bind(movie)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieItemEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieItemEntity,
                newItem: MovieItemEntity
            ): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(
                oldItem: MovieItemEntity,
                newItem: MovieItemEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
