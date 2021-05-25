package id.oktoluqman.moviet.ui.home.tv.list

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
import id.oktoluqman.moviet.core.domain.model.MovieTvItem
import id.oktoluqman.moviet.core.ui.MovieTvItemListAdapter
import id.oktoluqman.moviet.databinding.FragmentItemListBinding
import id.oktoluqman.moviet.ui.home.tv.detail.TvDetailActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TvListFragment : Fragment() {
    private lateinit var binding: FragmentItemListBinding
    private val viewModel by viewModels<TvListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
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

    companion object {
        private const val TAG = "TvListFragment"
    }
}
