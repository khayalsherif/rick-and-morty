package az.khayalsharifli.rickandmorty.ui.content.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import az.khayalsharifli.rickandmorty.R
import az.khayalsharifli.rickandmorty.base.BaseFragment
import az.khayalsharifli.rickandmorty.custom_view.RefreshView
import az.khayalsharifli.rickandmorty.databinding.FragmentHomeBinding
import az.khayalsharifli.rickandmorty.model.CharacterResult
import az.khayalsharifli.rickandmorty.tools.ClickListener
import az.khayalsharifli.rickandmorty.tools.DialogSelectedClickListener
import az.khayalsharifli.rickandmorty.ui.adapter.CharacterAdapter
import az.khayalsharifli.rickandmorty.ui.content.dialog.FilterBottomSheetDialog
import kotlinx.coroutines.launch
import me.dkzwm.widget.srl.RefreshingListenerAdapter
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), ClickListener,
    DialogSelectedClickListener {

    override val bindingCallBack: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate
    override val kClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val characterAdapter by lazy { CharacterAdapter(this) }

    private val resultList = ArrayList<CharacterResult>()

    override val bindViews: FragmentHomeBinding.() -> Unit = {
        configurationRecyclerView()
        loadData()
        configurationSearchView()
        setRefreshLayout()

        filter.setOnClickListener {
            val dialog = FilterBottomSheetDialog(this@HomeFragment)
            dialog.show(childFragmentManager, "dialog")
        }

    }

    private fun configurationRecyclerView() {
        binding.rcView.apply {
            adapter = characterAdapter
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
        }
    }

    private fun loadData() {
        viewModel.response.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                it.collect { data ->
                    binding.refreshLayout.refreshComplete(true)
                    characterAdapter.submitData(data)
                    resultList.clear()
                }
            }
        }
    }

    private fun configurationSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                viewModel.getDataByName(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun setRefreshLayout() {
        binding.refreshLayout.setHeaderView(RefreshView(requireContext()))
        binding.refreshLayout.setDisablePerformLoadMore(false)
        binding.refreshLayout.setEnableHeaderDrawerStyle(true)
        binding.refreshLayout.setMaxMoveRatioOfHeader(1f)
        binding.refreshLayout.setOnRefreshListener(object : RefreshingListenerAdapter() {
            override fun onRefreshing() {
                super.onRefreshing()
                viewModel.getAllData()
            }
        })
    }

    override fun onClick(position: Int, view: View) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                result = characterAdapter.snapshot().items[position]
            )
        )
    }

    override fun onDialogSelectedClick(id: Int, status: String, gender: String) {
        if (id == R.id.apply) {
            if (status != "" && gender != "") {
                viewModel.getDataByStatusAndGender(status = status, gender = gender)
            } else if (status != "") {
                viewModel.getDataByStatus(status = status)
            } else if (gender != "") {
                viewModel.getDataByGender(gender = gender)
            }
        }
    }

}