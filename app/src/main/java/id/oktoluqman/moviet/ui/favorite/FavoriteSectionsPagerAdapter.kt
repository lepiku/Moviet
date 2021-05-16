package id.oktoluqman.moviet.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.oktoluqman.moviet.ui.favorite.movie.list.MovieListFragment
import id.oktoluqman.moviet.ui.favorite.tv.list.TvListFragment

class FavoriteSectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieListFragment()
            1 -> TvListFragment()
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int = 2
}
