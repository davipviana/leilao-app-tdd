package com.davipviana.leilao.model

import java.io.Serializable
import java.util.*
import kotlin.math.min

class Leilao(val descricao: String) : Serializable {
    private val lances = ArrayList<Lance>()

    var maiorLance: Double = 0.0
        private set

    var menorLance: Double = 0.0
        private set

    fun proporLance(lance: Lance) {
        lances.add(lance)
        if(lances.size == 1) {
            maiorLance = lance.valor
            menorLance = lance.valor
        } else {
            lances.sort()
            calcularMaiorLance(lance)
            calcularMenorLance(lance)
        }
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

}
