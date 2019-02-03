package com.davipviana.leilao.ui.recyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.davipviana.leilao.R
import com.davipviana.leilao.model.Leilao

class LeiloesAdapter(private val context: Context, private val leiloes: List<Leilao>) :
    RecyclerView.Adapter<LeiloesAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.item_leilao, parent, false)
        return ViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val leilao = getLeilaoPorPosicao(position)
        holder.vincular(leilao)
    }

    override fun getItemCount(): Int {
        return leiloes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val descricao: TextView = itemView.findViewById(R.id.item_leilao_descricao)
        private val maiorLance: TextView = itemView.findViewById(R.id.item_leilao_maior_lance)
        private var leilao: Leilao? = null

        init {
            itemView.setOnClickListener { onItemClickListener!!.onItemClick(leilao) }
        }

        fun vincular(leilao: Leilao) {
            this.leilao = leilao
            descricao.text = leilao.descricao
            maiorLance.text = leilao.maiorLance.toString()
        }

    }

    fun getLeilaoPorPosicao(posicao: Int): Leilao {
        return this.leiloes[posicao]
    }

    interface OnItemClickListener {
        fun onItemClick(leilao: Leilao?)
    }

}