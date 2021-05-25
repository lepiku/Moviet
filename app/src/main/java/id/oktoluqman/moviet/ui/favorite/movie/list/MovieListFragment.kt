package id.oktoluqman.moviet.ui.favorite.movie.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.oktoluqman.moviet.databinding.FragmentItemListBinding
import id.oktoluqman.moviet.ui.adapter.MovieTvItemListAdapter
import id.oktoluqman.moviet.ui.movie.detail.MovieDetailActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var binding: FragmentItemListBinding? = null
    private val viewModel by viewModels<MovieListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { binding ->
            val adapter = MovieTvItemListAdapter { onClickItem(it) }

            binding.rvItems.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
                addItemDecoration(
                    DividerItemDecoration(binding.rvItems.context, DividerItemDecoration.VERTICAL)
                )
                contentDescription = TAG
            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.flow.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun onClickItem(id: Int) {
        Intent(requireContext(), MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.EXTRA_ID, id)
            startActivity(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val TAG = "MovieListFragment"
    }
}
