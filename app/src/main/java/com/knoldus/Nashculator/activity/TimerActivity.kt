package com.knoldus.Nashculator.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.knoldus.Nashculator.R
import com.knoldus.Nashculator.adapter.ViewPagerAdapter
import com.knoldus.Nashculator.fragment.CountdownTimerFragment
import com.knoldus.Nashculator.fragment.CounterFragment
import com.knoldus.Nashculator.fragment.StopwatchFragment
import kotlinx.android.synthetic.main.activity_calculator.*
/**
 * The activity for managing various timers.
 */
class TimerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        /**
         *    Customize the action bar
         */
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.timer)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        setUpTabs()          // Set up tabs for stopwatch, countdown timer, and counter fragments

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    /**
     * Set up the tabs for stopwatch, countdown timer, and counter fragments using a ViewPager.
     */
    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(StopwatchFragment(), getString(R.string.stopwatch))
        adapter.addFragment(CountdownTimerFragment(), getString(R.string.countdown_timer))
        adapter.addFragment(CounterFragment(), getString(R.string.counter))
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
