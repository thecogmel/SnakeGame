package com.example.snakegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.snakegame.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    lateinit var viewModel: ResultViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        val params = intent.extras
        val pontos = params?.getString("PONTOS")
        Log.i("PONTOSRESULT", "$pontos")

        binding.pontucaoFimDeJogo.text = pontos

        binding.buttonReiniciarJogo.setOnClickListener {
            var i = Intent(this, GameActivity::class.java)
            startActivity(i)
        }

        binding.buttonSairResultado.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}
