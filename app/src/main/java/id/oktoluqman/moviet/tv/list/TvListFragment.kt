package id.oktoluqman.moviet.tv.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.oktoluqman.moviet.databinding.FragmentItemListBinding
import id.oktoluqman.moviet.tv.detail.TvDetailActivity

class TvListFragment : Fragment() {
    private lateinit var binding: FragmentItemListBinding
    private lateinit var viewModel: TvListViewModel

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
            val adapter = TvListAdapter { onClickItem(it) }
            binding.rvItems.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
                addItemDecoration(
                    DividerItemDecoration(binding.rvItems.context, DividerItemDecoration.VERTICAL)
                )
            }

            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory(),
            )[TvListViewModel::class.java]
            viewModel.queryItemList()
            viewModel.getItemList().observe(viewLifecycleOwner) {
                adapter.setData(it)
            }
        }
    }

    private fun onClickItem(id: Int) {
        val intent = Intent(requireContext(), TvDetailActivity::class.java)
        intent.putExtra(TvDetailActivity.EXTRA_ID, id)
        startActivity(intent)
    }
}