package com.example.starwars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwars.databinding.FragmentPeopleBinding

import androidx.lifecycle.lifecycleScope
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.launch


class PeopleFragment : Fragment() {
    private lateinit var binding: FragmentPeopleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val response = apolloClient.query(PeopleQuery()).execute().also {}
            val peopleList = response?.data?.allPeople
            if (peopleList != null && !response.hasErrors()) {
                val adapter = Adapter(peopleList)

                adapter.onItemClicked = { people ->
                    findNavController().navigate(
                    PeopleFragmentDirections.actionPeopleFragmentToPeopleDetailFragment2(people.id))}
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerView.adapter = adapter
            }
        }


    }


}