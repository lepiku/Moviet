package id.oktoluqman.moviet.ui.home.movie.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.core.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.core.utils.TMDBConstants
import id.oktoluqman.moviet.databinding.ItemBinding
import id.oktoluqman.moviet.utils.Extensions.loadImage

class MovieListAdapter(val onClick: (id: Int) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.ListViewHolder>() {
    private val movieItems = ArrayList<MovieItemResponse>()

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBinding.bind(view)

        fun bind(movieItem: MovieItemResponse) {
            binding.imgThumbnail.loadImage(TMDBConstants.POSTER_SMALL_URL + movieItem.posterPath)
            binding.tvName.text = movieItem.title
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

    fun setData(items: List<MovieItemResponse>) {
        movieItems.clear()
        movieItems.addAll(items)
        notifyDataSetChanged()
    }
}
