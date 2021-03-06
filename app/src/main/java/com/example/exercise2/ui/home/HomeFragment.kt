package com.example.exercise2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise2.app.snackbar
import com.example.exercise2.databinding.HomeFragmentBinding
import com.example.exercise2.utils.EventObserver

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var shopsAdapter: ShopsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observes()
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.recyclerview.apply {
        viewModel.initShops()
        layoutManager = LinearLayoutManager(requireContext())
        shopsAdapter = ShopsAdapter()
        adapter = shopsAdapter
    }


    private fun observes() {

        viewModel.postLiveData.observe(viewLifecycleOwner, EventObserver(
            onError = {
                binding.progressBar.isVisible = false
                snackbar(it)
            },
            onLoading = {
                binding.progressBar.isVisible = true
            }

        ) {
            binding.progressBar.isVisible = false
            shopsAdapter.setData(it.shops.toMutableList())
        })
    }
}