package com.example.starwars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo3.api.Optional
import com.example.starwars.databinding.FragmentPeopleBinding
import com.example.starwars.databinding.FragmentPeopleDetailBinding
import kotlinx.coroutines.launch

class PeopleDetailFragment : Fragment() {
    private lateinit var binding: FragmentPeopleDetailBinding
    var peopleId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            peopleId = it.getString("peopleId").toString()
            Log.d("AAAA",peopleId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val response = apolloClient.query(GetPersonQuery(Optional.present(peopleId))).execute().also {
                Log.d("PeopleList",it.data.toString())}
            val peopleList = response?.data?.person
            if (peopleList != null && !response.hasErrors()) {
                binding.eyeColor.text = peopleList.eyeColor
                binding.hairColor.text = peopleList.hairColor
                binding.skinColor.text = peopleList.skinColor
                binding.birthYear.text = peopleList.birthYear
            }
        }
    }

}