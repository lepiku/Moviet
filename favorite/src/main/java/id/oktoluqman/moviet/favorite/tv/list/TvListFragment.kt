package id.oktoluqman.moviet.favorite.tv.list

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
import id.oktoluqman.moviet.core.databinding.FragmentItemListBinding
import id.oktoluqman.moviet.core.domain.model.MovieTvItem
import id.oktoluqman.moviet.core.ui.MovieTvItemListAdapter
import id.oktoluqman.moviet.favorite.FavoriteActivity
import id.oktoluqman.moviet.ui.home.tv.detail.TvDetailActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TvListFragment : Fragment() {
    private var binding: FragmentItemListBinding? = null

    private val viewModel by viewModels<TvListViewModel> {
        val activity = requireActivity() as FavoriteActivity
        activity.factory
    }

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

    private fun onClickItem(item: MovieTvItem) {
        Intent(requireContext(), TvDetailActivity::class.java).apply {
            putExtra(TvDetailActivity.EXTRA_ITEM, item)
            startActivity(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val TAG = "TvListFragment"
    }
}
