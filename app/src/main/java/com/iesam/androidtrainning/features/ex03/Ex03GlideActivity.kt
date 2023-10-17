package com.iesam.androidtrainning.features.ex03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.iesam.androidtrainning.app.extensions.setUrl
import com.iesam.androidtrainning.databinding.ActivityEx03GlideBinding

class Ex03GlideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEx03GlideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupView()
    }

    private fun setupBinding() {
        binding = ActivityEx03GlideBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView() {
        binding.let {
            it.imageUrl.setUrl("https://goo.gl/gEgYUd")
            it.imageUrl2.setUrl("")
        }
    }
}