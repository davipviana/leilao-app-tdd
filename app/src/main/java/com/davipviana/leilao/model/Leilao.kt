package com.davipviana.leilao.model

import java.io.Serializable
import java.util.ArrayList

class Leilao(val descricao: String) : Serializable {
    private val lances: List<Lance>
    var maiorLance: Double? = null
        private set

    init {
        this.lances = ArrayList()
    }

    fun proporLance(lance: Lance) {
        val maiorLance = this.maiorLance;
        if(maiorLance == null || lance.valor > maiorLance)
            this.maiorLance = lance.valor
    }

}
