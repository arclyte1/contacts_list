package com.example.content_provider_sample.presentation.contacts_list

import android.content.ContentResolver
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.content_provider_sample.R
import com.example.content_provider_sample.databinding.FragmentContactsListBinding
import com.example.content_provider_sample.domain.model.Contact

class ContactsListFragment: Fragment(R.layout.fragment_contacts_list) {

    private lateinit var binding: FragmentContactsListBinding
    private val adapter = ContactsListAdapter()
    private val viewModel: ContactsListViewModel by lazy { ContactsListViewModel(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContactsListBinding.bind(view)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.getContactsState.collect {
                adapter.submitList(it)
            }
        }

    }

}