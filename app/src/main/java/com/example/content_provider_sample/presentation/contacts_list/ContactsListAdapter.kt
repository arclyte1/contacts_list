package com.example.content_provider_sample.presentation.contacts_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.content_provider_sample.databinding.FragmentContactsListItemBinding
import com.example.content_provider_sample.domain.model.Contact

class ContactsListAdapter: ListAdapter<Contact, ContactsListAdapter.ViewHolder>(
object: DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }

}) {

//    var onClickListener: OnClickListener = OnClickListener {}

    inner class ViewHolder(binding: FragmentContactsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root
        val nameView = binding.name
        val numberView = binding.number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentContactsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.nameView.text = item.name
        holder.numberView.text = item.phoneNumber
//        holder.root.setOnClickListener {
//            onClickListener.onClick(item.id)
//        }
    }

//    class OnClickListener(val clickListener: (restaurantId: Long) -> Unit) {
//        fun onClick(restaurantId: Long) = clickListener(restaurantId)
//    }
}