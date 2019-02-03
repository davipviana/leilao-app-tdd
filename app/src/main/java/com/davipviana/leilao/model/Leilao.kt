package com.davipviana.leilao.model

import java.io.Serializable
import java.lang.RuntimeException
import java.util.*
import kotlin.math.min

class Leilao(val descricao: String) : Serializable {
    private val lances = ArrayList<Lance>()

    var maiorLance: Double = 0.0
        private set

    var menorLance: Double = 0.0
        private set

    fun proporLance(lance: Lance) {
        if (verificarLanceInvalido(lance)) return

        lances.add(lance)
        if (carregarValoresExtremosNoPrimeiroLance(lance)) return

        lances.sort()
        calcularMaiorLance(lance)
        calcularMenorLance(lance)
    }

    private fun carregarValoresExtremosNoPrimeiroLance(lance: Lance): Boolean {
        if (lances.size == 1) {
            maiorLance = lance.valor
            menorLance = lance.valor
            return true
        }
        return false
    }

    private fun verificarLanceInvalido(lance: Lance): Boolean {
        if(lance.valor < maiorLance) throw RuntimeException()

        if (!lances.isEmpty()) {
            if (lance.usuario == lances[0].usuario) throw RuntimeException()
            if (verificarQuantidadeMaximaDeLances(lance)) return true
        }
        return false
    }

    private fun verificarQuantidadeMaximaDeLances(lance: Lance): Boolean {
        var cont = 0
        for (l in lances) {
            if (l.usuario == lance.usuario)
                cont++

            if (cont == 5)
                return true
        }
        return false
    }

    private fun calcularMenorLance(lance: Lance) {
        val menorLance = this.menorLance
        if (lance.valor < menorLance)
            this.menorLance = lance.valor
    }

    private fun calcularMaiorLance(lance: Lance) {
        val maiorLance = this.maiorLance
        if (lance.valor > maiorLance)
            this.maiorLance = lance.valor
    }

    fun getTresMaioresLances(): List<Lance> {
        return lances.subList(0, min(3, lances.size))
    }

    fun getQuantidadeDeLances(): Int {
        return this.lances.size
    }

}
