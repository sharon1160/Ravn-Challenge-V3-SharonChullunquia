package com.example.starwars

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.PeopleItemBinding

class Adapter(
    private val peopleList: PeopleQuery.AllPeople
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val binding: PeopleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return peopleList.people?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PeopleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        peopleList.people?.let{
            val peopleQuery = it[position]
            holder.binding.name.text = peopleQuery?.name ?: "No data"
            val specieName = peopleQuery?.species?.name ?: "No data"
            val homeworldName = peopleQuery?.homeworld?.name ?: "No data"

            holder.binding.resume.text = "$specieName from $homeworldName"


        }
    }
}