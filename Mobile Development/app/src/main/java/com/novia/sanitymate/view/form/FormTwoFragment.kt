package com.novia.sanitymate.view.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.novia.sanitymate.R
import com.novia.sanitymate.databinding.FragmentFormTwiBinding
import com.novia.sanitymate.view.viewmodel.PredictionViewModel

class FormTwoFragment : Fragment() {

    private var _binding: FragmentFormTwiBinding? = null
    private val binding get() = _binding!!

    private val predictionViewModel: PredictionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFormTwiBinding.inflate(inflater, container, false)
        setUpHint()
        handleBackPress()
        setUpComponent()
        observeViewModel()
        setUpSubmitButton()
        return binding.root
    }

    private fun setUpComponent() {
        binding.appBar.barIndicator.progress = 100
    }

    private fun handleBackPress() {
        binding.appBar.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            val text = binding.etDesc.text.toString()
            if (text.isNotEmpty()) {
                predictionViewModel.postPrediction(text)
            } else {
                binding.etDesc.error = "This field cannot be empty"
            }
        }
    }

    private fun setUpHint() {
        val args: FormTwoFragmentArgs by navArgs()
        val resultEmotion = args.emotion
        val hint = when (resultEmotion) {
            1 -> "What's on your mind? Why are you worried?"
            2 -> "Why do you feel sad? Can you tell us more about it?"
            3 -> "What triggers your anger?"
            4 -> "Tell us more about what makes you happy!"
            else -> "Can you tell us more about these feelings of love?"
        }
        binding.etDesc.hint = hint
    }

    private fun observeViewModel() {
        predictionViewModel.recommendation.observe(viewLifecycleOwner) { recommendation ->
            if (recommendation != null) {
                val emotion = recommendation.emotion
                val action = FormTwoFragmentDirections.actionFormTwoFragmentToPredictingFragment(emotion)
                findNavController().navigate(action)
            }
        }

        predictionViewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                Snackbar.make(binding.root, "Please Fill First", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.black))
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}