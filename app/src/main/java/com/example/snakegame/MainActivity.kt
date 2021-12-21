package com.example.snakegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.snakegame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val intentConfig = Intent(this, ConfigActivity::class.java)

        binding.configButton.setOnClickListener {
            startActivity(intentConfig)
        }

        val intentRecord = Intent(this, ResultActivity::class.java)

        binding.recordButton.setOnClickListener {
            startActivity(intentRecord)
        }

        val intentNewgame = Intent(this, GameActivity::class.java)

        binding.newGameButton.setOnClickListener {
            startActivity(intentNewgame)
        }

    }
}