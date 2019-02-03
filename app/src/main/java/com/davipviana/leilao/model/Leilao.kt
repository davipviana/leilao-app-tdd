package com.davipviana.leilao.model

import java.io.Serializable
import java.util.*
import kotlin.math.min

class Leilao(val descricao: String) : Serializable {
    private val lances = ArrayList<Lance>()

    var maiorLance: Double? = null
        private set

    var menorLance: Double? = null
        private set

    fun proporLance(lance: Lance) {
        lances.add(lance)
        lances.sort()
        calcularMaiorLance(lance)
        calcularMenorLance(lance)
    }

    private fun calcularMenorLance(lance: Lance) {
        val menorLance = this.menorLance
        if (menorLance == null || lance.valor < menorLance)
            this.menorLance = lance.valor
    }

    private fun calcularMaiorLance(lance: Lance) {
        val maiorLance = this.maiorLance
        if (maiorLance == null || lance.valor > maiorLance)
            this.maiorLance = lance.valor
    }

    fun getTresMaioresLances(): List<Lance> {
        return lances.subList(0, min(3, lances.size))
    }

}
