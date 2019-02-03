package com.davipviana.leilao.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.davipviana.leilao.R
import com.davipviana.leilao.model.Leilao
import com.davipviana.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter
import java.util.*

class LeiloesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leiloes)
        val adapter = ListaLeilaoAdapter(this, leiloesDeExemplo())
        val recyclerView = findViewById<RecyclerView>(R.id.leiloes_recyclerview)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : ListaLeilaoAdapter.OnItemClickListener {
            override fun onItemClick(leilao: Leilao?) {
                val vaiParaLancesLeilao = Intent(this@LeiloesActivity, DetalhesLeilao::class.java)
                vaiParaLancesLeilao.putExtra("leilao", leilao)
                startActivity(vaiParaLancesLeilao)
            }
        })
    }

    private fun leiloesDeExemplo(): List<Leilao> {
        val console = Leilao("Console")
        return ArrayList<Leilao>(
            Arrays.asList<Leilao>(
                console
            )
        )
    }
}
