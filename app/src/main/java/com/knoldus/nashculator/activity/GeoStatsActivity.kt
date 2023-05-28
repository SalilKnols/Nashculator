package com.knoldus.nashculator.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.knoldus.nashculator.R
import com.knoldus.nashculator.adapter.ViewPagerAdapter
import com.knoldus.nashculator.databinding.ActivityCalculatorBinding
import com.knoldus.nashculator.fragment.GeometryFragment
import com.knoldus.nashculator.fragment.StaticalFregment

class GeoStatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.Geometry_Statics)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        setUpTabs()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(GeometryFragment(), getString(R.string.geometry))
        adapter.addFragment(StaticalFregment(), getString(R.string.statical))
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}


///////////////////////////////---------------------------------//////////////



