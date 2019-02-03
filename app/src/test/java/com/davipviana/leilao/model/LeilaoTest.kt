package com.davipviana.leilao.model

import org.junit.Test

import org.junit.Assert.*

class LeilaoTest {

    @Test
    fun getDescricao_RecebeDescricaoNoConstrutor_RetornaDescricaoRecebida() {
        // Criar cenario de teste
        val leilao = Leilao("Console")

        // Executar acao a ser testada
        val descricaoObtida = leilao.descricao

        // Testar resultado esperado
        assertEquals("Console", descricaoObtida)
    }

    @Test
    fun getMaiorLance_UmLance_RetornaValorLance() {
        val leilao = Leilao("Console")
        leilao.proporLance(Lance(Usuario("Usuario A"), 200.0))

        val maiorLanceObtido = leilao.maiorLance

        assertEquals(200.0, maiorLanceObtido)
    }

    @Test
    fun getMaiorLance_DoisLancesOrdemCrescente_RetornaMaiorValorLance() {
        val leilao = Leilao("Console")
        leilao.proporLance(Lance(Usuario("Usuario A"), 100.0))
        leilao.proporLance(Lance(Usuario("Usuario B"), 200.0))

        val maiorLanceObtido = leilao.maiorLance

        assertEquals(200.0, maiorLanceObtido)
    }

    @Test
    fun getMaiorLance_DoisLancesOrdemDecrescente_RetornaMaiorValorLance() {
        val leilao = Leilao("Console")
        leilao.proporLance(Lance(Usuario("Usuario A"), 200.0))
        leilao.proporLance(Lance(Usuario("Usuario B"), 100.0))

        val maiorLanceObtido = leilao.maiorLance

        assertEquals(200.0, maiorLanceObtido)
    }

    @Test
    fun getMenorLance_UmLance_RetornaValorLance() {
        val leilao = Leilao("Console")
        leilao.proporLance(Lance(Usuario("Usuario A"), 200.0))

        val menorLanceObtido = leilao.menorLance

        assertEquals(200.0, menorLanceObtido)
    }

    @Test
    fun getMenorLance_DoisLancesOrdemCrescente_RetornaMenorValorLance() {
        val leilao = Leilao("Console")
        leilao.proporLance(Lance(Usuario("Usuario A"), 100.0))
        leilao.proporLance(Lance(Usuario("Usuario B"), 200.0))

        val menorLanceObtido = leilao.menorLance

        assertEquals(100.0, menorLanceObtido)
    }

    @Test
    fun getMaiorLance_DoisLancesOrdemDecrescente_RetornaMenorValorLance() {
        val leilao = Leilao("Console")
        leilao.proporLance(Lance(Usuario("Usuario A"), 200.0))
        leilao.proporLance(Lance(Usuario("Usuario B"), 100.0))

        val menorLanceObtido = leilao.menorLance

        assertEquals(100.0, menorLanceObtido)
    }
}