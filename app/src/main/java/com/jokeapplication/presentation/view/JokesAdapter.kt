package com.jokeapplication.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jokeapplication.R
import com.jokeapplication.data.model.JokesModel

class JokesAdapter(private val jokesList: List<JokesModel>) :
    RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {

    inner class JokesViewHolder(iteView: View) : ViewHolder(iteView) {
        private var tvJoke: TextView = itemView.findViewById(R.id.tv_joke)

        fun bind(jokesModel: JokesModel) {
            tvJoke.text = jokesModel.joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {
        return JokesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_jokes, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return jokesList.size
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        holder.bind(jokesList[position])
    }
}