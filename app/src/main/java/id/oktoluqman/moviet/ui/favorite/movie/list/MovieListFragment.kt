package id.oktoluqman.moviet.ui.favorite.movie.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.oktoluqman.moviet.databinding.FragmentItemListBinding
import id.oktoluqman.moviet.ui.movie.detail.MovieDetailActivity

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var binding: FragmentItemListBinding? = null
    private val viewModel by viewModels<MovieListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(layoutInflater, container, false)
        binding!!.rvItems.contentDescription = TAG

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { binding ->
            val adapter = MovieListAdapter { onClickItem(it) }

            binding.rvItems.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
                addItemDecoration(
                    DividerItemDecoration(binding.rvItems.context, DividerItemDecoration.VERTICAL)
                )
            }
            viewModel.getItemList().observe(viewLifecycleOwner) {
                adapter.setData(it)
            }
        }
    }

    private fun onClickItem(id: Int) {
        val intent = Intent(requireContext(), MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.EXTRA_ID, id)
        startActivity(intent)
    }

    companion object {
        private const val TAG = "MovieListFragment"
    }
}
