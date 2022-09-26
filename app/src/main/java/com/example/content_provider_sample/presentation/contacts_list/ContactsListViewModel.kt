package com.example.content_provider_sample.presentation.contacts_list

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.content_provider_sample.domain.model.Contact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactsListViewModel(
    private val context: Context
): ViewModel() {

    private val _getContactsState = MutableStateFlow<List<Contact>>(emptyList())
    val getContactsState: StateFlow<List<Contact>> = _getContactsState

    init {
        getContacts()
    }

    @SuppressLint("Range")
    fun getContacts() {
        viewModelScope.launch {
            val contentResolver = context.contentResolver
            val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            val cursor = contentResolver.query(uri, null, null, null, null)
            if (cursor!!.count > 0) {
                val list = mutableListOf<Contact>()
                while (cursor.moveToNext()) {
                    val contact = Contact(
                        name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    )
                    list.add(contact)
                    cursor.moveToNext()
                }
                _getContactsState.value = list
            }
        }
    }

}