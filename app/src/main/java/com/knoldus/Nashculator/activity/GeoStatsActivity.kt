package com.knoldus.Nashculator.activity

import GeometryFragment
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.knoldus.Nashculator.R
import com.knoldus.Nashculator.adapter.ViewPagerAdapter
import com.knoldus.Nashculator.databinding.ActivityCalculatorBinding
import com.knoldus.Nashculator.fragment.StaticalFregment

/**
 * The activity for displaying geometry and statistical calculations.
 */
class GeoStatsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /**
         *    Customize the action bar
         */
        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.Geometry_Statics)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        setUpTabs()         // Set up tabs for geometry and statistical fragments

    }

    /**
     * Navigation Support Functionality to navigate either forward or back
     */
    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    /**
     * Set up the tabs for geometry and statistical fragments using a ViewPager.
     */
    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(GeometryFragment(), getString(R.string.geometry))
        adapter.addFragment(StaticalFregment(), getString(R.string.statical))
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}




