package com.example.cleanarch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarch.databinding.ActivityMyBinding
import com.example.cleanarch.di.TimeNowFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.paginationTask.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragmentContainer, CleanArchFragment.newInstance())
                .commit()
        }

        binding.diTask.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragmentContainer, TimeNowFragment.newInstance())
                .commit()
        }
    }

}