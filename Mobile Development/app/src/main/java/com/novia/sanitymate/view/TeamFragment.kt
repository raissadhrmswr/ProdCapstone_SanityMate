package com.novia.sanitymate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.novia.sanitymate.R
import com.novia.sanitymate.databinding.FragmentTeamBinding
import com.novia.sanitymate.model.Team
import com.novia.sanitymate.view.adapter.TeamAdapter

class TeamFragment : Fragment() {

    private var _binding: FragmentTeamBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<Team>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.addAll(getTeam())
        showRecyclerList()
        handleBackPress()
    }


    private fun handleBackPress(){
        binding.appBar.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun getTeam(): ArrayList<Team> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataUniv = resources.getStringArray(R.array.data_universitas)
        val dataLearningPath = resources.getStringArray(R.array.data_path)
        val dataPhoto = resources.obtainTypedArray(R.array.data_avatar)
        val listTeam = ArrayList<Team>()
        for (i in dataName.indices) {
            val hero = Team(dataName[i], dataUniv[i],dataLearningPath[i], dataPhoto.getResourceId(i, -1))
            listTeam.add(hero)
        }
        return listTeam
    }

    private fun showRecyclerList() {
        binding.rvTeam.layoutManager = LinearLayoutManager(requireActivity())
        val listHeroAdapter = TeamAdapter(list)
        binding.rvTeam.adapter = listHeroAdapter
    }
}