package com.example.exercise2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise2.databinding.HomeFragmentBinding
import com.example.exercise2.model.shops.Shop

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var shopsAdapter: ShopsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel._shopsLiveData.observe(viewLifecycleOwner, {
            shopsAdapter.setData(it.shops as MutableList<Shop>)

        })

        viewModel.init()
        setupRecyclerView()
    }

   private fun setupRecyclerView() = binding.recyclerview.apply {
        layoutManager = LinearLayoutManager(requireContext())
        shopsAdapter = ShopsAdapter()
        adapter = shopsAdapter
    }

}