package com.example.snakegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.snakegame.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    lateinit var viewModel: GameActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)
        viewModel = ViewModelProvider(this).get(GameActivityViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        val inflater = LayoutInflater.from(this)

        var i = Intent(this, ResultActivity::class.java)


        val params = intent.extras
        //val dificuldade  = params?.getLong("DIFICULDADE")
        //Log.i("DIFICULDADE", "$dificuldade")
        //val tamanho_tabuleiro =  params?.getInt("TAMANHO_TABULEIRO")
        //Log.i("TAM_TABULEIRO", "$tamanho_tabuleiro")

        // viewModel.alterarDificuldade(dificuldade)
        // viewModel.alterarTabuleiro(tamanho_tabuleiro, tamanho_tabuleiro)


        viewModel.mudarMovimento(1)

        loadTabuleiro(inflater)

        viewModel.starGame()


        runGame(i)






        binding.buttonMoverCima.setOnClickListener {
            viewModel.mudarMovimento(1)
        }
        binding.buttonMoverBaixo.setOnClickListener {
            viewModel.mudarMovimento(2)
        }
        binding.buttonMoverEsquerda.setOnClickListener {
            viewModel.mudarMovimento(3)
        }
        binding.buttonMoverDireita.setOnClickListener {
            viewModel.mudarMovimento(4)
        }


    }


    fun runGame(intent : Intent) {

        Thread {
            while (viewModel.gameStatus.value!! == true) {
                Thread.sleep(1000)
                runOnUiThread {
                    viewModel.limpaTabuleiro()

                    viewModel.printFruta(viewModel.posicaoFrutaLinha.value!!, viewModel.posicaoFrutaColuna.value!!)
                    viewModel.comeuFruta(viewModel.posicaoFrutaLinha.value!!, viewModel.posicaoFrutaColuna.value!!)
                    viewModel.gameOver()
                    viewModel.refreshCobra()
                    viewModel.printCobra()
                    viewModel.snakeMovement(viewModel.direcao.value!!)
                }
            }

            var b = Bundle()
            b.putString("PONTOS", viewModel.pontos.value!!.toString())
            intent.putExtras(b)
            startActivity(intent)
        }.start()

    }

    private fun loadTabuleiro(inflater: LayoutInflater) {
        for (i in 0..(viewModel.row.value!! - 1)) {
            Log.i("FOR1", "pos: $i")
            for (j in 0..(viewModel.column.value!! - 1)) {
                Log.i("FOR2", "pos: $i")
                val im = inflater.inflate(
                    R.layout.cobra,
                    binding.gridLayout,
                    false
                ) as ImageView
                viewModel.tabuleiro.value!![i][j] = im
                binding.gridLayout.addView(viewModel.tabuleiro.value!![i][j])
            }
        }
    }
}