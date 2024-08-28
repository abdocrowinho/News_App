package com.example.newsapp.bases

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<Vb :ViewBinding > : AppCompatActivity() {
  private var _binding :Vb ?=null
    val binding :Vb get()  = _binding!!
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        _binding = DataBindingUtil.setContentView(this,getLayoutId())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    abstract  fun getLayoutId():Int
}