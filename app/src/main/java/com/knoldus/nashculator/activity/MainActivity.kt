package com.knoldus.nashculator.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.knoldus.nashculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        binding.btCalculator.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }

        binding.btConverter.setOnClickListener {
            startActivity(Intent(this, GeoStatsActivity::class.java))
        }

//        binding.btTimer.setOnClickListener {
//            startActivity(Intent(this, TimerActivity::class.java))
//        }
    }
}
