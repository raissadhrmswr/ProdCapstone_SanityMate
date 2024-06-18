package com.novia.sanitymate.view.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.novia.sanitymate.R
import com.novia.sanitymate.databinding.FragmentFormOneBinding

class FormOneFragment : Fragment() {

    private var _binding: FragmentFormOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFormOneBinding.inflate(inflater, container, false)
        setUpComponent()
        passEmotion()
        handleBackPress()
        return binding.root
    }

    private fun setUpComponent(){
        binding.appBar.barIndicator.progress = 50
    }

    private fun handleBackPress(){
        binding.appBar.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun passEmotion(){
        binding.btnNext.setOnClickListener {
            val selectedRadioButtonId = binding.radioAll.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val selectedEmotion = when (selectedRadioButtonId) {
                    R.id.rd_worry -> 1
                    R.id.rd_sad -> 2
                    R.id.rd_anger -> 3
                    R.id.rd_happy -> 4
                    R.id.rd_love -> 5
                    else -> throw IllegalStateException("Unexpected radio button ID")
                }
                val action = FormOneFragmentDirections.actionFormOneFragmentToFormTwoFragment(selectedEmotion)
                findNavController().navigate(action)
            } else {
                Snackbar.make(binding.root, "Please select your mood.", Snackbar.LENGTH_SHORT)
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