package id.oktoluqman.moviet.ui.favorite.tv.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.data.source.local.entity.TvItemEntity
import id.oktoluqman.moviet.databinding.ItemBinding
import id.oktoluqman.moviet.utils.Extensions.loadImage
import id.oktoluqman.moviet.utils.TMDBConstants

class TvListAdapter(val onClick: (id: Int) -> Unit) :
    PagingDataAdapter<TvItemEntity, TvListAdapter.ListViewHolder>(diffCallback) {

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBinding.bind(view)

        fun bind(tvItem: TvItemEntity) {
            binding.imgThumbnail.loadImage(TMDBConstants.POSTER_SMALL_URL + tvItem.posterPath)
            binding.tvName.text = tvItem.name
            binding.tvOverview.text = tvItem.overview
            binding.card.setOnClickListener { onClick(tvItem.tvId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null)
            holder.bind(tv)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TvItemEntity>() {
            override fun areItemsTheSame(
                oldItem: TvItemEntity,
                newItem: TvItemEntity
            ): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(
                oldItem: TvItemEntity,
                newItem: TvItemEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
