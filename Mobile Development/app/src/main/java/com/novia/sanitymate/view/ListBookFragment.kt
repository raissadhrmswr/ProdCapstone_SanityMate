package com.novia.sanitymate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.novia.sanitymate.R
import com.novia.sanitymate.databinding.FragmentListBookBinding
import com.novia.sanitymate.model.ItemBook
import com.novia.sanitymate.view.adapter.ListBookAdapter

class ListBookFragment : Fragment() {

    private var _binding: FragmentListBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ListBookFragmentArgs by navArgs()
        val articlesArray = args.listBook
        val recom = args.recomendation
        binding.tvRecommendation.text = recom
        val articles: List<ItemBook> = articlesArray.toList()
        setupRecyclerView(articles)

        getBack()
    }

    private fun getBack(){
        binding.appBar.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_listBookFragment_to_chooseFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Navigation.findNavController(binding.root).navigate(R.id.action_listBookFragment_to_chooseFragment)
            }
        })
    }

    private fun setupRecyclerView(articles: List<ItemBook>) {
        val recyclerView: RecyclerView = binding.rvListBook
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ListBookAdapter(articles)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}