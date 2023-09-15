package com.besirkaraoglu.bankingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.besirkaraoglu.bankingapp.MainViewModel
import com.besirkaraoglu.bankingapp.R
import com.besirkaraoglu.bankingapp.core.Result
import com.besirkaraoglu.bankingapp.core.viewBinding
import com.besirkaraoglu.bankingapp.databinding.FragmentMainBinding
import com.besirkaraoglu.bankingapp.model.RequestResult
import com.besirkaraoglu.bankingapp.navigateTo
import com.besirkaraoglu.bankingapp.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), BankAdapter.ItemClickListener {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by activityViewModels<MainViewModel>()

    private var adapter: BankAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initData()
    }

    private fun initComponents() {
        adapter = BankAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.etSearchField.addTextChangedListener {
            if (!it.isNullOrEmpty()){
                (viewModel.filterList(it.toString()) as? MutableList<RequestResult>)?.let { it1 ->
                    adapter?.setList(
                        it1
                    )
                }
            }else{
                (viewModel.filterList("") as? MutableList<RequestResult>)?.let { it1 ->
                    adapter?.setList(
                        it1
                    )
                }
            }
        }
    }

    private fun initData() {
        viewModel.getData()
        viewModel.bankData.observe(viewLifecycleOwner){result ->
            binding.pb.visibility = if (result is Result.Loading) View.VISIBLE else View.INVISIBLE
            when(result){
                is Result.Failure -> {
                    result.e.localizedMessage?.let { showSnackbar(it) }
                }
                Result.Loading -> {}
                is Result.Success -> {
                    adapter!!.setList(result.data as MutableList<RequestResult>)
                }
            }

        }
    }

    override fun onClick(item: RequestResult) {
        viewModel.setCurrentItem(item)
        navigateTo(R.id.action_mainFragment_to_detailFragment)
    }

}