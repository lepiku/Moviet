package id.oktoluqman.moviet.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.oktoluqman.moviet.core.R
import id.oktoluqman.moviet.core.databinding.ItemBinding
import id.oktoluqman.moviet.core.domain.model.MovieTvItem
import id.oktoluqman.moviet.core.utils.TMDBConstants
import id.oktoluqman.moviet.core.utils.Extensions.loadImage

class MovieTvItemListAdapter(val onClick: (item: MovieTvItem) -> Unit) :
    PagingDataAdapter<MovieTvItem, MovieTvItemListAdapter.ListViewHolder>(diffCallback) {

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBinding.bind(view)

        fun bind(item: MovieTvItem) {
            binding.imgThumbnail.loadImage(TMDBConstants.POSTER_SMALL_URL + item.posterPath)
            binding.tvName.text = item.name
            binding.tvOverview.text = item.overview
            binding.card.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<MovieTvItem>() {
            override fun areItemsTheSame(
                oldItem: MovieTvItem,
                newItem: MovieTvItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieTvItem,
                newItem: MovieTvItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
