package id.oktoluqman.moviet.ui.favorite.movie.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.databinding.ItemBinding
import id.oktoluqman.moviet.utils.TMDBConstants

class MovieListAdapter(val onClick: (id: Int) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.ListViewHolder>() {
    private val movieItems = ArrayList<MovieItemEntity>()

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBinding.bind(view)

        fun bind(movieItem: MovieItemEntity) {
            Glide.with(itemView.context)
                .load(TMDBConstants.POSTER_SMALL_URL + movieItem.posterPath)
                .placeholder(R.drawable.ic_baseline_refresh_24).centerCrop()
                .error(R.drawable.ic_baseline_broken_image_24).centerCrop()
                .into(binding.imgThumbnail)
            binding.tvTvDetailName.text = movieItem.title
            binding.tvOverview.text = movieItem.overview
            binding.card.setOnClickListener { onClick(movieItem.movieId) }
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

    fun setData(items: List<MovieItemEntity>) {
        movieItems.clear()
        movieItems.addAll(items)
        notifyDataSetChanged()
    }
}
