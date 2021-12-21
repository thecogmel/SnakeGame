package com.example.snakegame

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class GameActivityViewModel : ViewModel() {
    private var _gameStatus = MutableLiveData<Boolean>(false)
    var gameStatus: LiveData<Boolean> = _gameStatus

    private var _direcao = MutableLiveData<Int>(0)
    var direcao: LiveData<Int> = _direcao


    private var _pontos = MutableLiveData<Int>(0)
    var pontos: LiveData<Int> = _pontos


    private var _velocidade = MutableLiveData<Long>(
        1 // verificar qual o valor de timeout as threads milisegundos
    )
    var velocidade: LiveData<Long> = _velocidade

    private var _row = MutableLiveData<Int>(24)

    var row: LiveData<Int> = _row

    private var _column = MutableLiveData<Int>(24)
    var column: LiveData<Int> = _column

    private var _posicaoFrutaLinha = MutableLiveData<Int>((0 until row.value!! - 1).random())
    var posicaoFrutaLinha: LiveData<Int> = _posicaoFrutaLinha

    private var _posicaoFrutaColuna = MutableLiveData<Int>((0 until column.value!! - 1).random())
    var posicaoFrutaColuna: LiveData<Int> = _posicaoFrutaColuna

    private var _tabuleiro = MutableLiveData(
        Array(row.value!!) { kotlin.arrayOfNulls<ImageView>(column.value!!) }
    )
    var tabuleiro: LiveData<Array<Array<ImageView?>>> = _tabuleiro

    private var _listPosicaoCobra = MutableLiveData(
        mutableListOf<Ponto>()
    )

    var listPosicaoCobra: LiveData<MutableList<Ponto>> = _listPosicaoCobra


    //----------------------------------FUNCTIONS-------------------------------------//


    fun starGame() {
        _gameStatus.value = true
        _listPosicaoCobra.value!!.add(Ponto((row.value!! / 2), (column.value!! / 2)))
    }

    fun gameOver() {
        if ((listPosicaoCobra.value!!.get(0).x == (row.value!! - 1) || listPosicaoCobra.value!!.get(
                0
            ).y == (column.value!! - 1)) || (listPosicaoCobra.value!!.get(0).x == -1 || listPosicaoCobra.value!!.get(
                0
            ).y == -1)
        ) {
            _gameStatus.value = false
        }
    }


    //----------------------FRUTA-------------------------------------------------------//
    fun printFruta(x: Int, y: Int) {
        tabuleiro.value!![x][y]!!.setImageResource(R.drawable.fruta)
    }

    fun comeuFruta(x: Int, y: Int) {
        if (listPosicaoCobra.value!!.get(0).x == x && listPosicaoCobra.value!!.get(0).y == y) {
            // _listPosicaoCobra.value!!.add(Ponto(x, y))
            _pontos.value = _pontos.value!!.plus(200)

            _posicaoFrutaLinha.value = (0 until row.value!! - 1).random()
            _posicaoFrutaColuna.value = (0 until column.value!! - 1).random()
        }
    }


    //--------------------------COBRA---------------------------------------------//


    fun refreshCobra() {
        if (gameStatus.value!! == true) {

            for (i in 1 until listPosicaoCobra.value!!.size) {
                listPosicaoCobra.value!![i] = listPosicaoCobra.value!![i - 1]
            }


        }
    }

    fun printCobra() {
        if (gameStatus.value!! == true) {

            // if (listPosicaoCobra.value!!.size > 1) {
            // for (i in 1 until listPosicaoCobra.value!!.size) {
            tabuleiro.value!![listPosicaoCobra.value!!.get(0).x][listPosicaoCobra.value!!.get(0).y]!!.setImageResource(
                R.drawable.cabeca
            )
            // tabuleiro.value!![listPosicaoCobra.value!!.get(i).x][listPosicaoCobra.value!!.get(i).y]!!.setImageResource(R.drawable.corpo_cobra)
            //   Log.i("COBRA", "${listPosicaoCobra.value.toString()}")

            //  }
            // } else {
            //   tabuleiro.value!![listPosicaoCobra.value!!.get(0).x][listPosicaoCobra.value!!.get(0).y]!!.setImageResource(
            //        R.drawable.cabeca
            //    )
//  }

            // }
        }
    }

    fun mudarMovimento(movimento: Int) {
        _direcao.value = movimento
    }


    fun snakeMovement(movimento: Int) {
        if (gameStatus.value!! == true) {
            for (i in 0 until listPosicaoCobra.value!!.size) {
                when (movimento) {
                    //cima
                    1 -> listPosicaoCobra.value!!.get(0).x = listPosicaoCobra.value!!.get(0).x - 1
                    //baixo
                    2 -> listPosicaoCobra.value!!.get(0).x = listPosicaoCobra.value!!.get(0).x + 1
                    //esquerda
                    3 -> listPosicaoCobra.value!!.get(0).y = listPosicaoCobra.value!!.get(0).y - 1
                    //direita
                    4 -> listPosicaoCobra.value!!.get(0).y = listPosicaoCobra.value!!.get(0).y + 1
                }
            }
        }

    }

    //-------------------------TABULEIRO----------------------------------------------------------
    fun limpaTabuleiro() {
        for (i in 0 until row.value!!) {
            for (j in 0 until (column.value!!)) {
                tabuleiro.value!![i][j]!!.setImageResource(R.drawable.background)
            }
        }
    }


    // colocar essa funcoes na viewModel de configuracoes
    fun alterarTabuleiro(row : Int?, column : Int?) {
        _row.value = row
        _column.value = column
    }

    fun alterarDificuldade(valorVelocidade : Long?) {
        _velocidade.value = valorVelocidade
    }
}