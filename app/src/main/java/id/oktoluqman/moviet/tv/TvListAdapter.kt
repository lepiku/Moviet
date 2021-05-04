package id.oktoluqman.moviet.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.data.TvItem
import id.oktoluqman.moviet.databinding.ItemBinding

class TvListAdapter(val onClick: (id: Int) -> Unit) :
    RecyclerView.Adapter<TvListAdapter.ListViewHolder>() {
    private val tvItems = ArrayList<TvItem>()

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBinding.bind(view)

        fun bind(tvItem: TvItem) {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w92${tvItem.posterPath}")
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

    fun setData(items: List<TvItem>) {
        tvItems.clear()
        tvItems.addAll(items)
        notifyDataSetChanged()
    }
}