package com.davipviana.leilao.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.davipviana.leilao.R
import com.davipviana.leilao.model.Leilao

class DetalhesLeilao : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_leilao)
        val dadosRecebidos = intent
        if (dadosRecebidos.hasExtra("leilao")) {
            val leilao = dadosRecebidos.getSerializableExtra("leilao") as Leilao
            val descricao = findViewById<TextView>(R.id.lances_leilao_descricao)
            descricao.text = leilao.descricao
            val maiorLance = findViewById<TextView>(R.id.lances_leilao_maior_lance)
            maiorLance.text = leilao.maiorLance.toString()
        }
    }
}
