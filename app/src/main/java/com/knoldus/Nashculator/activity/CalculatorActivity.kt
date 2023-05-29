package com.knoldus.Nashculator.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.knoldus.Nashculator.R
import com.knoldus.Nashculator.adapter.ViewPagerAdapter
import com.knoldus.Nashculator.fragment.BasicCalculatorFragment
import com.knoldus.Nashculator.fragment.ScientificCalculatorFragment
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.calculator)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setBackgroundDrawable(ColorDrawable(Color.BLACK))

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
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}