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
/**
 * The activity for displaying a calculator with basic and scientific functionality.
 */
class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        /**
         *    Customize the action bar
         */
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.calculator)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        setUpTabs()          // Set up tabs for geometry and statistical fragments
    }

    /**
     * Navigation Support Functionality to navigate either forward or back
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    /**
      *   Set up tabs for basic and scientific calculator fragments
     */
    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(BasicCalculatorFragment(), getString(R.string.basic_calculator))
        adapter.addFragment(ScientificCalculatorFragment(), getString(R.string.scientific_calculator))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}