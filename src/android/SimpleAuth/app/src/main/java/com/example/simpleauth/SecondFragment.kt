package com.example.simpleauth

import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startForegroundService
import com.example.simpleauth.databinding.FragmentSecondBinding
import com.example.simpleauth.helpers.PreferenceHelper
import com.example.simpleauth.services.FetchService


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 * This Fragment makes data fetch through the FetchService
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs =
            PreferenceHelper.customPreference(this.requireActivity(), MainActivity.AUTH_DATA)
        val editor = sharedPrefs.edit()

        binding.logoutButton.setOnClickListener {
            // Stop fetching
            this.requireContext().stopService(Intent(this.requireContext(), FetchService::class.java))

            editor.clear()
            editor.apply()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, FirstFragment()) .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}