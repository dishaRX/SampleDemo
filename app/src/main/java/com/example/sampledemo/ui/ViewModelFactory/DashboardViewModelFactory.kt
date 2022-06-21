package com.example.sampledemo.ui.ViewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampledemo.R
import com.example.sampledemo.ui.viewmodel.DashboardViewModel

class DashboardViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
                return DashboardViewModel(context) as T
            }
            throw IllegalArgumentException(context.resources.getString(R.string.unknown_viewmodel))
        }
    }
