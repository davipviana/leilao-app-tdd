package com.davipviana.leilao.model

import java.io.Serializable

class Lance(val usuario: Usuario, val valor: Double) : Serializable, Comparable<Lance> {
    override fun compareTo(other: Lance): Int {
        if(this.valor > other.valor)
            return -1
        if(this.valor < other.valor)
            return 1

        return 0
    }

}