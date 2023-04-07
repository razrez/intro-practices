package com.example.cleanarch

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarch.databinding.FragmentCleanArchBinding
import com.example.cleanarch.recycler.adapters.CustomRecyclerAdapter
import com.example.core.base.FragmentBase
import java.util.*

class CleanArchFragment : FragmentBase<FragmentCleanArchBinding, CleanArchViewModel>(
    R.id.mainFragmentContainer
) {
    override fun setUpViews() {
        super.setUpViews()

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        binding.loadSmthBtn.setOnClickListener {

            // clears list
            recyclerView.adapter = CustomRecyclerAdapter(emptyList())

            // fetches one random post
            viewModel.getUserData(Random().nextInt(50))

        }

        binding.pagination.setOnClickListener {
            // clears text view
            binding.resultTextView.text = ""

            // fetches 5-10 users
            val rnd = kotlin.random.Random.nextInt(5,10)
            viewModel.getUsers(rnd)
        }
    }

    override fun observeData() {
        super.observeData()

        viewModel.userDataMutable.observe(this) {
            if (it == null) binding.resultTextView.text = "Данные пусты"
            else binding.resultTextView.text = it.toString()
        }

        viewModel.usersMutableList.observe(this){
            binding.recyclerView.adapter = CustomRecyclerAdapter(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CleanArchFragment()
    }

    override fun getViewModelClass(): Class<CleanArchViewModel> {
        return CleanArchViewModel::class.java
    }

    override fun getViewBinding(): FragmentCleanArchBinding {
        return FragmentCleanArchBinding.inflate(layoutInflater)
    }

    // Let's create a useless list of strings for now, which we will pass to the adapter.
    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i element") }
        return data
    }
}