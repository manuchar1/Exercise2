package com.example.exercise2.ui.active_orders

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exercise2.R

class ActiveOrdersFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveOrdersFragment()
    }

    private lateinit var viewModel: ActiveOrdersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.active_orders_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActiveOrdersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}