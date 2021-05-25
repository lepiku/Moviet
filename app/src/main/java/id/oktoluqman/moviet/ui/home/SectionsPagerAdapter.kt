package id.oktoluqman.moviet.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.oktoluqman.moviet.ui.home.movie.list.MovieListFragment
import id.oktoluqman.moviet.ui.home.tv.list.TvListFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieListFragment()
            1 -> TvListFragment()
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int = 2
}
