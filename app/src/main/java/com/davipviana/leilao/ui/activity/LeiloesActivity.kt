package com.davipviana.leilao.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.davipviana.leilao.R
import com.davipviana.leilao.model.Lance
import com.davipviana.leilao.model.Leilao
import com.davipviana.leilao.model.Usuario
import com.davipviana.leilao.ui.recyclerview.adapter.LeiloesAdapter
import java.util.*

class LeiloesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leiloes)
        val adapter = LeiloesAdapter(this, leiloesDeExemplo())
        val recyclerView = findViewById<RecyclerView>(R.id.leiloes_recyclerview)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : LeiloesAdapter.OnItemClickListener {
            override fun onItemClick(leilao: Leilao?) {
                val vaiParaLancesLeilao = Intent(this@LeiloesActivity, DetalhesLeilao::class.java)
                vaiParaLancesLeilao.putExtra("leilao", leilao)
                startActivity(vaiParaLancesLeilao)
            }
        })
    }

    private fun leiloesDeExemplo(): List<Leilao> {
        val leilaoConsole = Leilao("Console")
        leilaoConsole.proporLance(Lance(Usuario("Jose"), 200.0))
        leilaoConsole.proporLance(Lance(Usuario("João"), 300.0))

        val leilaoComputador = Leilao("Computador")
        leilaoComputador.proporLance(Lance(Usuario("Maria"), 1000.0))

        val leilaoCarro = Leilao("Carro")
        leilaoCarro.proporLance(Lance(Usuario("Joana"), 10000.0))
        leilaoCarro.proporLance(Lance(Usuario("Mario"), 15000.0))
        leilaoCarro.proporLance(Lance(Usuario("Ana"), 17000.0))

        return ArrayList<Leilao>(
            Arrays.asList<Leilao>(
                leilaoConsole,
                leilaoComputador,
                leilaoCarro
            )
        )
    }
}
