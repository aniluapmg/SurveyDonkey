package com.desafiolatam.surveydonkey.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.desafiolatam.surveydonkey.databinding.ActivityMainBinding
import com.desafiolatam.surveydonkey.ui.adapter.SurveyPagerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewPager: SurveyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = SurveyPagerAdapter(this)
        binding.vpMain.adapter = viewPager

        binding.fab.setOnClickListener {
            when (binding.vpMain.currentItem) {
                0 -> binding.vpMain.setCurrentItem(1, true)
                1 -> binding.vpMain.setCurrentItem(2, true)
                2 -> binding.vpMain.setCurrentItem(3, true)
                3 -> binding.vpMain.setCurrentItem(4, true)
                4 -> binding.vpMain.setCurrentItem(5, true)
            }
        }
    }
}