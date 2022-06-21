package com.example.sampledemo.ui.viewmodel

import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampledemo.R
import com.example.sampledemo.data.api.ApiResponseCallBack
import com.example.sampledemo.data.api.ReturnType
import com.example.sampledemo.data.model.DashboardResponse
import com.example.sampledemo.utills.LoadingDialog
import com.example.sampledemo.utills.LogUtils
import com.example.sampledemo.utills.Utility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.radian.myradianvaluations.networking.ApiServiceProviderGeneric

class DashboardViewModel(private val context: Context) : ViewModel(), ApiResponseCallBack {
    private var dashboardPostResponse = MutableLiveData<MutableList<DashboardResponse>>()

    private val apiServiceProviderGeneric = ApiServiceProviderGeneric(this)

    val dashboardPostData: MutableLiveData<MutableList<DashboardResponse>>
        get() = dashboardPostResponse

    fun getDashboardPostData() {
        apiServiceProviderGeneric.getCall(
            context,
            ReturnType.GET_DashboardPost.endPoint,
            ReturnType.GET_DashboardPost,""
        )
    }

    override fun onPreExecute(returnType: ReturnType) {
        LoadingDialog.show(context)
    }

    override fun onSuccess(returnType: ReturnType, response: String) {
        LoadingDialog.dismissDialog()
        try {
            when (returnType) {
                ReturnType.GET_DashboardPost -> {
                    val response = Gson().fromJson<List<DashboardResponse>>(
                        response,
                        object : TypeToken<List<DashboardResponse>>() {}.type
                    )
                    dashboardPostResponse.value = response as MutableList<DashboardResponse>?
                }
            }

        } catch (e: Exception) {
            LogUtils.logE("", e)
        }
    }

    override fun onError(returnType: ReturnType, error: String) {
        LoadingDialog.dismissDialog()
        Utility.showOkDialog(
            context!!,
            context.getString(R.string.please_try_again),
            DialogInterface.OnClickListener { _, _ -> },
            context.getString(R.string.ok)
        )
    }

}