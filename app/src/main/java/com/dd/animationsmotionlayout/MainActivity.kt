package com.dd.animationsmotionlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dd.animationsmotionlayout.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAlphaAnimation.setOnClickListener { Animations.alphaAnimationOf(binding.ivIcon) }
        binding.btnScalingAnimation.setOnClickListener { Animations.scalingAnimationOf(binding.ivIcon) }
        binding.btnRollingAnimation.setOnClickListener { Animations.rollingAnimationOf(binding.ivIcon) }
        binding.btnChangeBackground.setOnClickListener { Animations.changeBackgroundOf(binding.ivIcon, this) }
        binding.btnRainyAnimation.setOnClickListener { Animations.rainyAnimationOf(binding.ivIcon, this) }
    }
}