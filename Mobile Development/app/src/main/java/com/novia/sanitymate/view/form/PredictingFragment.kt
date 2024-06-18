package com.novia.sanitymate.view.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.novia.sanitymate.R
import com.novia.sanitymate.databinding.FragmentPredictingBinding
import com.novia.sanitymate.model.ItemBook
import com.novia.sanitymate.view.viewmodel.RecommendationViewModel

class PredictingFragment : Fragment() {

    private var _binding: FragmentPredictingBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RecommendationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPredictingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[RecommendationViewModel::class.java]

        val args: PredictingFragmentArgs by navArgs()
        val emotion = args.emotion

        viewModel.recommendationResponse.observe(viewLifecycleOwner) { response ->
            val articles = response?.data?.articles ?: emptyList()
            val recom = response?.data?.recommendation
            if (response != null) {
                if (recom != null) {
                    navigateToNextDestination(articles, recom)
                }
            } else {
                Snackbar.make(binding.root, "Something Wrong.", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.black))
                    .show()
            }
        }

        viewModel.progress.observe(viewLifecycleOwner) { progress ->
            binding.barIndicator.progress = progress
        }

        viewModel.fetchRecommendation(emotion)
    }

    private fun navigateToNextDestination(articles: List<ItemBook>, text: String) {
        val action = PredictingFragmentDirections
            .actionPredictingFragmentToListBookFragment(getEmotion(), articles.toTypedArray(), text)
        findNavController().navigate(action)
    }

    private fun getEmotion(): String {
        val args: PredictingFragmentArgs by navArgs()
        return args.emotion
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}