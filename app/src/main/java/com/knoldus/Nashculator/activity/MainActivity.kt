package com.knoldus.Nashculator.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.knoldus.Nashculator.R
import com.knoldus.Nashculator.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        binding.btTimer2.setOnClickListener {
            val intent = Intent(this@MainActivity, GeoStatsActivity::class.java)
            startActivity(intent)
        }
        btCalculator.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }

        btConverter.setOnClickListener {
            startActivity(Intent(this, UnitConverterActivity::class.java))
        }

        btTimer.setOnClickListener {
            startActivity(Intent(this, TimerActivity::class.java))
        }


    }

}

