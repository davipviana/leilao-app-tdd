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
    fun deve_DevolverTresMaioresLancesOrdenados_QuandoRecebeTresLances() {
        leilao.proporLance(Lance(usuarioUm, 300.0))
        leilao.proporLance(Lance(Usuario("Usuario 2"), 200.0))
        leilao.proporLance(Lance(usuarioUm, 400.0))

        val lancesObtidos = leilao.getTresMaioresLances()

        assertEquals(3, lancesObtidos.size)
        assertEquals(400.0, lancesObtidos[0].valor, 0.0001)
        assertEquals(300.0, lancesObtidos[1].valor, 0.0001)
        assertEquals(200.0, lancesObtidos[2].valor, 0.0001)
    }

    @Test
    fun deve_DevolverTresMaioresLancesOrdenados_QuandoNaoRecebeLances() {
        val lancesObtidos = leilao.getTresMaioresLances()

        assertEquals(0, lancesObtidos.size)
    }

    @Test
    fun deve_DevolverTresMaioresLancesOrdenados_QuandoRecebeUmLance() {
        leilao.proporLance(Lance(usuarioUm, 300.0))
        val lancesObtidos = leilao.getTresMaioresLances()

        assertEquals(1, lancesObtidos.size)
        assertEquals(300.0, lancesObtidos[0].valor, 0.0001)
    }

    @Test
    fun deve_DevolverTresMaioresLancesOrdenados_QuandoRecebeDoisLances() {
        leilao.proporLance(Lance(usuarioUm, 300.0))
        leilao.proporLance(Lance(Usuario("Usuario 2"), 400.0))
        val lancesObtidos = leilao.getTresMaioresLances()

        assertEquals(2, lancesObtidos.size)
        assertEquals(400.0, lancesObtidos[0].valor, 0.0001)
        assertEquals(300.0, lancesObtidos[1].valor, 0.0001)
    }

    @Test
    fun deve_DevolverTresMaioresLancesOrdenados_QuandoRecebeMaisDeTresLances() {
        leilao.proporLance(Lance(usuarioUm, 300.0))
        leilao.proporLance(Lance(Usuario("Usuario 2"), 500.0))
        leilao.proporLance(Lance(Usuario("Usuario 3"), 400.0))
        leilao.proporLance(Lance(Usuario("Usuario 4"), 700.0))
        val lancesObtidos = leilao.getTresMaioresLances()

        assertEquals(3, lancesObtidos.size)
        assertEquals(700.0, lancesObtidos[0].valor, 0.0001)
        assertEquals(500.0, lancesObtidos[1].valor, 0.0001)
        assertEquals(400.0, lancesObtidos[2].valor, 0.0001)

        leilao.proporLance(Lance(usuarioUm, 800.0))
        val lancesObtidos2 = leilao.getTresMaioresLances()

        assertEquals(3, lancesObtidos2.size)
        assertEquals(800.0, lancesObtidos2[0].valor, 0.0001)
        assertEquals(700.0, lancesObtidos2[1].valor, 0.0001)
        assertEquals(500.0, lancesObtidos2[2].valor, 0.0001)
    }
}