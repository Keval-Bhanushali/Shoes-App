package com.example.e_commerce.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.Adapter.BrandsAdapter
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
    }

    private fun initBrands() {
        binding.recyclerViewBrands.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewBrands.adapter = brandsAdapter
        binding.progressBarCategory.visibility = View.VISIBLE
    }
}