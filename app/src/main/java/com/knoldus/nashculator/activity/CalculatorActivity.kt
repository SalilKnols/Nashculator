package com.knoldus.nashculator.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.knoldus.nashculator.fragment.BasicCalculatorFragment
import com.knoldus.nashculator.fragment.ScientificCalculatorFragment
import com.knoldus.nashculator.R
import com.knoldus.nashculator.adapter.ViewPagerAdapter
import com.knoldus.nashculator.databinding.ActivityCalculatorBinding

@Suppress("DEPRECATION")
class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.calculator)
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
        adapter.addFragment(BasicCalculatorFragment(), getString(R.string.basic_calculator))
        adapter.addFragment(ScientificCalculatorFragment(), getString(R.string.scientific_calculator))
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}
