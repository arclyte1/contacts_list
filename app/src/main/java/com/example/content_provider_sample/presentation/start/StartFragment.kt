package com.example.content_provider_sample.presentation.start

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.content_provider_sample.R
import com.example.content_provider_sample.databinding.FragmentStartBinding

class StartFragment: Fragment(R.layout.fragment_start) {

    private lateinit var binding: FragmentStartBinding

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                updateUI()
            } else {
                binding.button.visibility = View.VISIBLE
                binding.textView2.visibility = View.VISIBLE
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentStartBinding.bind(view)
        binding.button.setOnClickListener {
            requestPermissionLauncher.launch(
                Manifest.permission.READ_CONTACTS)
        }

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            updateUI()
        }
        else {
            requestPermissionLauncher.launch(
                Manifest.permission.READ_CONTACTS)
        }
    }

    fun updateUI() {
        findNavController().navigate(R.id.action_startFragment_to_contactsListFragment)
    }
}