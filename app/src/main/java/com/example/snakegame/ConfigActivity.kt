package com.example.snakegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.snakegame.databinding.ActivityConfigBinding

class ConfigActivity : AppCompatActivity() {
    lateinit var binding : ActivityConfigBinding

    lateinit var viewModel: ConfigViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_config)
        viewModel = ViewModelProvider(this).get(ConfigViewModel::class.java)

        var i = Intent(this, GameActivity::class.java)
        var b = Bundle()


        binding.salvarCfg.setOnClickListener {
            if(binding.radioButtonFacil.isSelected){
                b.putLong("DIFICULDADE", 1000)
            }else if (binding.radioButtonMedio.isSelected){
                b.putLong("DIFICULDADE", 1000/2)
            }else if (binding.radioButtonDificil.isSelected){
                b.putLong("DIFICULDADE", 1000/4)
            }

            if (binding.radioButton24x24.isSelected){
                b.putInt("TAMANHO_TABULEIRO", 24)

            }else if (binding.radioButton48x48.isSelected){
                b.putInt("TAMANHO_TABULEIRO", 48)
            }

            intent.putExtras(b)
            startActivity(i)
        }

    }
}