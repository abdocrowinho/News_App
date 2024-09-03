package com.example.newsapp.bases

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<Vb : ViewBinding> : AppCompatActivity() {
    private var _binding: Vb? = null
    val binding: Vb get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        installSplashScreen()
        _binding = DataBindingUtil.setContentView(this, getLayoutId())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showDialog(
        message: String,
        posText: String? = null,
        negText: String? = null,
        posCallBack: onDialogClick? = null,
        negCallBack: onDialogClick? = null,
        isCancelable : Boolean = true

    ) {
        val builder = AlertDialog.Builder(this)
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
        builder.setCancelable(isCancelable)


    }
    fun showDialog(
       @StringRes message: Int,
       @StringRes posText: Int? = null,
       @StringRes negText: Int? = null,
        posCallBack: onDialogClick? = null,
        negCallBack: onDialogClick? = null,
        isCancelable : Boolean = true

    ) {
        val builder = AlertDialog.Builder(this)
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
        builder.setCancelable(isCancelable)


    }

    fun interface onDialogClick {
        fun onDialogClick(): DialogInterface
    }

    abstract fun getLayoutId(): Int
}