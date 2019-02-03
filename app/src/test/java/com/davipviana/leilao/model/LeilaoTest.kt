package com.davipviana.leilao.model

import org.junit.Test

import org.junit.Assert.*

class LeilaoTest {

    private val leilao = Leilao("Console")
    private val usuarioUm = Usuario("Usuario 1")

    @Test
    fun getDescricao_RecebeDescricaoNoConstrutor_RetornaDescricaoRecebida() {
        val descricaoObtida = leilao.descricao

        assertEquals("Console", descricaoObtida)
    }

    @Test
    fun getMaiorLance_UmLance_RetornaValorLance() {
        leilao.proporLance(Lance(usuarioUm, 200.0))

        val maiorLanceObtido = leilao.maiorLance

        assertEquals(200.0, maiorLanceObtido)
    }

    @Test
    fun getMaiorLance_DoisLancesOrdemCrescente_RetornaMaiorValorLance() {
        val usuarioDois = Usuario("Usuario 2")
        leilao.proporLance(Lance(usuarioUm, 100.0))
        leilao.proporLance(Lance(usuarioDois, 200.0))

        val maiorLanceObtido = leilao.maiorLance

        assertEquals(200.0, maiorLanceObtido)
    }

    @Test
    fun getMaiorLance_DoisLancesOrdemDecrescente_RetornaMaiorValorLance() {
        val usuarioDois = Usuario("Usuario 2")
        leilao.proporLance(Lance(usuarioUm, 200.0))
        leilao.proporLance(Lance(usuarioDois, 100.0))

        val maiorLanceObtido = leilao.maiorLance

        assertEquals(200.0, maiorLanceObtido)
    }

    @Test
    fun getMenorLance_UmLance_RetornaValorLance() {
        leilao.proporLance(Lance(usuarioUm, 200.0))

        val menorLanceObtido = leilao.menorLance

        assertEquals(200.0, menorLanceObtido)
    }

    @Test
    fun getMenorLance_DoisLancesOrdemCrescente_RetornaMenorValorLance() {
        val usuarioDois = Usuario("Usuario 2")
        leilao.proporLance(Lance(usuarioUm, 100.0))
        leilao.proporLance(Lance(usuarioDois, 200.0))

        val menorLanceObtido = leilao.menorLance

        assertEquals(100.0, menorLanceObtido)
    }

    @Test
    fun getMaiorLance_DoisLancesOrdemDecrescente_RetornaMenorValorLance() {
        val usuarioDois = Usuario("Usuario 2")
        leilao.proporLance(Lance(usuarioUm, 200.0))
        leilao.proporLance(Lance(usuarioDois, 100.0))

        val menorLanceObtido = leilao.menorLance

        assertEquals(100.0, menorLanceObtido)
    }

    @Test
    fun deve_DevolverTresMaioresLances_QuandoRecebeTresLances() {
        leilao.proporLance(Lance(usuarioUm, 200.0))
        leilao.proporLance(Lance(Usuario("Usuario 2"), 300.0))
        leilao.proporLance(Lance(usuarioUm, 400.0))

        val lancesObtidos = leilao.getTresMaioresLances()

        assertEquals(3, lancesObtidos.size)
    }
}