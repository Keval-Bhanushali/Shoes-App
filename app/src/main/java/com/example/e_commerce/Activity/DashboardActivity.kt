package com.example.e_commerce.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.e_commerce.Adapter.BrandsAdapter
import com.example.e_commerce.Adapter.SliderAdapter
import com.example.e_commerce.Model.SliderModel
import com.example.e_commerce.ViewModel.MainViewModel
import com.example.e_commerce.databinding.ActivityMainBinding

class DashboardActivity : AppCompatActivity() {
    private val ViewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    private val brandsAdapter = BrandsAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initBrands()
        initBanner()
    }

    private fun initBrands() {
        binding.recyclerViewBrands.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewBrands.adapter = brandsAdapter
        binding.progressBarBrands.visibility = View.VISIBLE

        ViewModel.brands.observe(this) { data ->
            brandsAdapter.updateData(data)
            binding.progressBarBrands.visibility = View.GONE
        }

        ViewModel.loadBrands()
    }

    private fun setupBanners(image: List<SliderModel>) {
        binding.viewpagerSlider.apply {
            adapter = SliderAdapter(image, this)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            (getChildAt(0) as? RecyclerView)?.overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
            })
        }
        binding.dotIndiactor.apply {
            visibility = if (image.size > 1) View.VISIBLE else View.GONE
            if (image.size > 1) attachTo(binding.viewpagerSlider)
        }
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        ViewModel.banners.observe(this) {
            items->
            setupBanners(items)
            binding.progressBarBanner.visibility = View.GONE
        }
        ViewModel.loadBanners()
    }
}