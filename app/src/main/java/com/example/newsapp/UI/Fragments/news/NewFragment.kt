package com.example.newsapp.UI.Fragments.news

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.newsapp.Apis.ApiManger
import com.example.newsapp.Apis.Models.Sources.ResourcesResponse
import com.example.newsapp.Apis.Models.Sources.SourcesItem
import com.example.newsapp.R
import com.example.newsapp.bases.BaseFragment
import com.example.newsapp.databinding.FragmentNewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewFragment : BaseFragment<FragmentNewBinding>() {
    val args : NewFragmentArgs by navArgs()
    override fun getLayoutId(): Int {
        return R.layout.fragment_new
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSources()
    }

    private fun getSources() {
        loadingDialog(getString(R.string.loading), isCancelable = false)
        ApiManger.getInstance().getSources(category = args.category.id).enqueue(object : Callback<ResourcesResponse> {
            override fun onResponse(
                call: Call<ResourcesResponse>,
                response: Response<ResourcesResponse>
            ) {
                if (response.isSuccessful) {
                    hideLoadingDialog()
                    showNewsSources(response.body()?.sources)
                } else {
                    hideLoadingDialog()
                    handleError(response) {}
                }

            }

            override fun onFailure(call: Call<ResourcesResponse>, response: Throwable) {
                hideLoadingDialog()
                handleError(response, posAction = {
                    getSources()
                }


                )
                Log.e("fail", response.localizedMessage)

            }


        })
    }

    private fun showNewsSources(sources: List<SourcesItem?>?) {
        sources?.forEach{sources ->
            val tap = binding.tabSources.newTab()
            tap.setText(sources?.name)
            binding.tabSources.addTab(tap)
        }

    }
}