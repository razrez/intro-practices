package com.example.cleanarch.di

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleanarch.databinding.FragmentTimeNowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeNowFragment : Fragment() {

    companion object {
        fun newInstance() = TimeNowFragment()
    }

    private lateinit var viewModel: TimeNowViewModel
    private lateinit var binding: FragmentTimeNowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentTimeNowBinding.inflate(layoutInflater)

        binding.getTimeButton.setOnClickListener {
            viewModel.getTime()
        }

        //return inflater.inflate(R.layout.fragment_time_now, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TimeNowViewModel::class.java)

        viewModel.timeNow.observe(this) {
            binding.timeResultTextView.text = it
        }
    }

}