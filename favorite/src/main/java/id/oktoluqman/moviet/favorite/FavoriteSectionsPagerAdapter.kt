package id.oktoluqman.moviet.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.oktoluqman.moviet.favorite.movie.list.MovieListFragment
import id.oktoluqman.moviet.favorite.tv.list.TvListFragment

class FavoriteSectionsPagerAdapter(private val activity: FavoriteActivity) :
    FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieListFragment(activity)
            1 -> TvListFragment(activity)
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int = 2
}
