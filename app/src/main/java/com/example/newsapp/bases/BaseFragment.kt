package com.example.newsapp.bases

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.newsapp.Apis.Models.BaseResponse
import com.example.newsapp.R
import com.example.newsapp.fromJson
import java.io.IOException
import java.net.UnknownHostException

abstract class BaseFragment<vb : ViewBinding> : Fragment() {
    private var _binding: vb? = null
    var dialog: ProgressDialog? = null
    val binding: vb get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    abstract fun getLayoutId(): Int

    /* Loading Dialog  */

    fun loadingDialog(// with String message
        message: String,
        isCancelable: Boolean = true
    ) {
        showLoadingDialog(message, isCancelable)


    }

    fun loadingDialog( // with String Resources message
        @StringRes message: Int,
        isCancelable: Boolean = true
    ) {
        showLoadingDialog(getString(message), isCancelable)
    }

    private fun showLoadingDialog(message: String, cancelable: Boolean) {
        dialog = ProgressDialog(activity)
        dialog?.setMessage(message)
        dialog?.setCancelable(cancelable)
        dialog?.show()
    }

    fun hideLoadingDialog() {
        dialog?.dismiss()
    }

    /* Loading Dialog  */

    /* ( Show Dialog for Handel Error  */
    fun showDialog( // with String texts
        message: String,
        posText: String? = null,
        negText: String? = null,
        posCallBack: OnDialogClick? = null,
        negCallBack: OnDialogClick? = null,
        isCancelable: Boolean = true

    ) {
        val builder = AlertDialog.Builder(requireContext())
            .setMessage(message)
        posText?.let {
            builder.setPositiveButton(
                posText
            ) { dialog, i ->
                dialog.dismiss()
                posCallBack?.onDialogClick()


            }
        }
        negText?.let {
            builder.setNegativeButton(
                negText
            ) { dialog, i ->
                dialog.dismiss()
                negCallBack?.onDialogClick()
            }
        }
        builder.show()
        builder.setCancelable(isCancelable)


    }

    fun showDialog( // with String Resources texts
        @StringRes message: Int,
        @StringRes posText: Int? = null,
        @StringRes negText: Int? = null,
        posCallBack: OnDialogClick? = null,
        negCallBack: OnDialogClick? = null,
        isCancelable: Boolean = true

    ) {

        val builder = AlertDialog.Builder(requireContext())
            .setMessage(message)
        posText?.let {
            builder.setPositiveButton(
                posText
            ) { dialog, i ->
                dialog.dismiss()
                posCallBack?.onDialogClick()


            }
        }
        negText?.let {
            builder.setNegativeButton(
                negText
            ) { dialog, i ->
                dialog.dismiss()
                negCallBack?.onDialogClick()
            }
        }
        builder.show()
        builder.setCancelable(isCancelable)


    }
    /* Show Dialog for Handel Error ) */


    fun interface OnDialogClick { //callBack's Dialogs
        fun onDialogClick()
    }

    /* Handling Error */
    fun <T> handleError(response: retrofit2.Response<T>, posAction: OnDialogClick) {
        //handler Error Function Takes Response's Retrofit
        val errorResponse = response.errorBody()?.string()?.fromJson(BaseResponse::class.java)
        showDialog(
            errorResponse?.message ?: getString(R.string.something_went_wrong),
            posText = getString(R.string.retry),
            posCallBack = posAction
        )
    }

    fun handleError(throwable: Throwable, posAction: OnDialogClick) {
        //handler Error Function Takes Throwable response
        val message = when (throwable) {
            is UnknownHostException,
            is IOException -> {
                getString(R.string.connection_error)
            }

            else -> getString(R.string.something_went_wrong)
        }
        showDialog(
            message,
            posText = getString(R.string.retry),
            posCallBack = posAction
        )
    }
    /* Handling Error */
}