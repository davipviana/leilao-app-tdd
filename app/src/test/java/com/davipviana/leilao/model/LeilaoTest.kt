package com.davipviana.leilao.model

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.ExpectedException
import java.lang.RuntimeException

class LeilaoTest {

    private val leilao = Leilao("Console")
    private val usuarioUm = Usuario("Usuario 1")

    @Rule
    @JvmField
    val expectedException = ExpectedException.none()

    @Test
    fun getDescricao_RecebeDescricaoNoConstrutor_RetornaDescricaoRecebida() {
        val descricaoObtida = leilao.descricao

        assertEquals("Console", descricaoObtida)
    }

    @Test
    fun getMaiorLance_UmLance_RetornaValorLance() {
        leilao.proporLance(Lance(usuarioUm, 200.0))

        val maiorLanceObtido = leilao.maiorLance

        assertEquals(200.0, maiorLanceObtido, 0.0001)
    }

    @Test
    fun getMaiorLance_DoisLancesOrdemCrescente_RetornaMaiorValorLance() {
        val usuarioDois = Usuario("Usuario 2")
        leilao.proporLance(Lance(usuarioUm, 100.0))
        leilao.proporLance(Lance(usuarioDois, 200.0))

        val maiorLanceObtido = leilao.maiorLance

        assertEquals(200.0, maiorLanceObtido, 0.0001)
    }

    @Test
    fun getMenorLance_UmLance_RetornaValorLance() {
        leilao.proporLance(Lance(usuarioUm, 200.0))

        val menorLanceObtido = leilao.menorLance

        assertEquals(200.0, menorLanceObtido, 0.0001)
    }

    @Test
    fun getMenorLance_DoisLancesOrdemCrescente_RetornaMenorValorLance() {
        val usuarioDois = Usuario("Usuario 2")
        leilao.proporLance(Lance(usuarioUm, 100.0))
        leilao.proporLance(Lance(usuarioDois, 200.0))

        val menorLanceObtido = leilao.menorLance

        assertEquals(100.0, menorLanceObtido, 0.0001)
    }

    @Test
    fun deve_DevolverTresMaioresLancesOrdenados_QuandoRecebeTresLances() {
        leilao.proporLance(Lance(usuarioUm, 300.0))
        leilao.proporLance(Lance(Usuario("Usuario 2"), 350.0))
        leilao.proporLance(Lance(usuarioUm, 400.0))

        val lancesObtidos = leilao.getTresMaioresLances()

        assertEquals(3, lancesObtidos.size)
        assertEquals(400.0, lancesObtidos[0].valor, 0.0001)
        assertEquals(350.0, lancesObtidos[1].valor, 0.0001)
        assertEquals(300.0, lancesObtidos[2].valor, 0.0001)
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
        leilao.proporLance(Lance(Usuario("Usuario 3"), 600.0))
        leilao.proporLance(Lance(Usuario("Usuario 4"), 700.0))
        val lancesObtidos = leilao.getTresMaioresLances()

        assertEquals(3, lancesObtidos.size)
        assertEquals(700.0, lancesObtidos[0].valor, 0.0001)
        assertEquals(600.0, lancesObtidos[1].valor, 0.0001)
        assertEquals(500.0, lancesObtidos[2].valor, 0.0001)

        leilao.proporLance(Lance(usuarioUm, 800.0))
        val lancesObtidos2 = leilao.getTresMaioresLances()

        assertEquals(3, lancesObtidos2.size)
        assertEquals(800.0, lancesObtidos2[0].valor, 0.0001)
        assertEquals(700.0, lancesObtidos2[1].valor, 0.0001)
        assertEquals(600.0, lancesObtidos2[2].valor, 0.0001)
    }

    @Test
    fun deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances() {
        val maiorLanceObtido = leilao.maiorLance

        assertEquals(0.0, maiorLanceObtido, 0.0001)
    }

    @Test
    fun deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances() {
        val menorLanceObtido = leilao.menorLance

        assertEquals(0.0, menorLanceObtido, 0.0001)
    }

    @Test
    fun naoDeve_AdicionarLance_QuandoForMenorQueMaiorLance() {
        expectedException.expect(RuntimeException::class.java)

        leilao.proporLance(Lance(usuarioUm, 500.00))
        leilao.proporLance(Lance(Usuario("Usuario 2"), 400.00))
    }

    @Test
    fun naoDeve_AdicionarLance_QuandoForMesmoUsuarioDoLanceAnterior() {
        expectedException.expect(RuntimeException::class.java)

        leilao.proporLance(Lance(usuarioUm, 500.00))
        leilao.proporLance(Lance(Usuario("Usuario 1"), 600.00))

    }

    @Test
    fun naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances() {
        val usuarioDois = Usuario("Usuario 2")
        leilao.proporLance(Lance(usuarioUm, 100.00))
        leilao.proporLance(Lance(usuarioDois, 200.00))
        leilao.proporLance(Lance(usuarioUm, 300.00))
        leilao.proporLance(Lance(usuarioDois , 400.00))
        leilao.proporLance(Lance(usuarioUm, 500.00))
        leilao.proporLance(Lance(usuarioDois , 600.00))
        leilao.proporLance(Lance(usuarioUm, 700.00))
        leilao.proporLance(Lance(usuarioDois , 800.00))
        leilao.proporLance(Lance(usuarioUm, 900.00))
        leilao.proporLance(Lance(usuarioDois , 1000.00))
        leilao.proporLance(Lance(usuarioUm, 1100.00))

        val quantidadeDeLancesObtida = leilao.getQuantidadeDeLances()

        assertEquals(10, quantidadeDeLancesObtida)
    }
}