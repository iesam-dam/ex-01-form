package com.iesam.androidtrainning.features

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iesam.androidtrainning.R
import com.iesam.androidtrainning.features.ex01.presentation.Ex01FormActivity
import com.iesam.androidtrainning.features.ex03.Ex03GlideActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        findViewById<Button>(R.id.action_ex01_form).setOnClickListener {
            startActivity(Intent(this, Ex01FormActivity::class.java))
        }
        findViewById<Button>(R.id.action_ex03_glide).setOnClickListener {
            startActivity(Intent(this, Ex03GlideActivity::class.java))
        }
    }
}