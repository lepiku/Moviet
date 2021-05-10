package id.oktoluqman.moviet.ui.movie.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.data.MovieItem
import id.oktoluqman.moviet.databinding.ItemBinding
import id.oktoluqman.moviet.utils.TMDBConstants

class MovieListAdapter(val onClick: (id: Int) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.ListViewHolder>() {
    private val movieItems = ArrayList<MovieItem>()

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBinding.bind(view)

        fun bind(movieItem: MovieItem) {
            Glide.with(itemView.context)
                .load(TMDBConstants.POSTER_SMALL_URL + movieItem.posterPath)
                .into(binding.imgThumbnail)
            binding.tvTvDetailName.text = movieItem.title
            binding.tvOverview.text = movieItem.overview
            binding.card.setOnClickListener { onClick(movieItem.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(movieItems[position])
    }

    override fun getItemCount(): Int {
        return movieItems.size
    }

    fun setData(items: List<MovieItem>) {
        movieItems.clear()
        movieItems.addAll(items)
        notifyDataSetChanged()
    }
}