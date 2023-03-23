package com.example.simpleauth

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.simpleauth.MainActivity.Companion.AUTH_DATA
import com.example.simpleauth.helpers.PreferenceHelper.IS_AUTHORIZED
import com.example.simpleauth.helpers.PreferenceHelper.USER_TOKEN
import com.example.simpleauth.helpers.PreferenceHelper.customPreference
import com.example.simpleauth.databinding.FragmentFirstBinding
import com.example.simpleauth.services.FetchService

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 * Login Fragment
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefs = customPreference(this.requireActivity(), AUTH_DATA)
        val editor = sharedPrefs.edit()

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login

        loginButton.setOnClickListener {
            if (usernameEditText.text.toString().isEmpty() || passwordEditText.text.toString().isEmpty()){
                Toast(this.context).showCustomToast(getString(R.string.login_failed))
            }

            else {
                // imitate successful login
                val authRes = true
                editor.putString(USER_TOKEN, "secret")
                editor.putBoolean(IS_AUTHORIZED, authRes)
                editor.apply()

                if (authRes) {
                    this.requireContext().startService(Intent(this.requireContext(), FetchService::class.java))
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, SecondFragment()).setReorderingAllowed(false).commit()
                }

                else {
                    Toast(this.context).showCustomToast(getString(R.string.login_failed))
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Toast.showCustomToast(message: String)
    {
        // use the application extension function
        this.apply {
            setGravity(Gravity.BOTTOM, 0, 40)
            setText(message)
            duration = Toast.LENGTH_LONG
            show()
        }
    }

}