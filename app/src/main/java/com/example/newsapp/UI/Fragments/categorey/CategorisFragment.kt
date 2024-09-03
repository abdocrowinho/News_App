package com.example.newsapp.UI.Fragments.categorey

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.bases.BaseFragment
import com.example.newsapp.databinding.FragmentCategorisBinding


class CategorisFragment : BaseFragment<FragmentCategorisBinding>() {
    companion object {
        val category = "category"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        val adapter = CategoryesAdapter(categoryes.toMutableList())
        binding.contentHome.RcvCategores.adapter = adapter
        adapter.setOnCategoryClick = CategoryesAdapter.onClick { position, categoryModel ->
            val action =
                CategorisFragmentDirections.actionCategorisFragmentToNewFragment(categoryModel)

            findNavController().navigate(action)
        }
    }

    val categoryes =
        arrayOf(
            CategoryModel(
                R.drawable.binary_category_shape_red,
                "Sport",
                R.drawable.sports,
                "sports"
            ),
            CategoryModel(
                R.drawable.odd_category_shape_blue,
                "General",
                R.drawable.politics,
                " general"
            ),
            CategoryModel(
                R.drawable.binary_category_shape_purbel,
                "Health",
                R.drawable.health,
                "health"
            ),
            CategoryModel(
                R.drawable.odd_category_shape_brown,
                "Business",
                R.drawable.bussines,
                "business"
            ),
            CategoryModel(
                R.drawable.binary_category_shape_light_blue,
                "Technology",
                R.drawable.environment,
                "technology"
            ),
            CategoryModel(
                R.drawable.odd_category_shape_yellow,
                "Science",
                R.drawable.science,
                "science"
            )
        )


    override fun getLayoutId(): Int {
        return R.layout.fragment_categoris
    }

}