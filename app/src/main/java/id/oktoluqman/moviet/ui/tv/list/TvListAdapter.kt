package id.oktoluqman.moviet.ui.tv.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.data.source.remote.response.TvItemResponse
import id.oktoluqman.moviet.databinding.ItemBinding
import id.oktoluqman.moviet.utils.TMDBConstants

class TvListAdapter(val onClick: (id: Int) -> Unit) :
    RecyclerView.Adapter<TvListAdapter.ListViewHolder>() {
    private val tvItems = ArrayList<TvItemResponse>()

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBinding.bind(view)

        fun bind(tvItem: TvItemResponse) {
            Glide.with(itemView.context)
                .load(TMDBConstants.POSTER_SMALL_URL + tvItem.posterPath)
                .placeholder(R.drawable.ic_baseline_refresh_24).centerCrop()
                .error(R.drawable.ic_baseline_broken_image_24).centerCrop()
                .into(binding.imgThumbnail)
            binding.tvTvDetailName.text = tvItem.name
            binding.tvOverview.text = tvItem.overview
            binding.card.setOnClickListener { onClick(tvItem.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(tvItems[position])
    }

    override fun getItemCount(): Int {
        return tvItems.size
    }

    fun setData(items: List<TvItemResponse>) {
        tvItems.clear()
        tvItems.addAll(items)
        notifyDataSetChanged()
    }
}
