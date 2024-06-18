package com.novia.sanitymate.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.novia.sanitymate.R
import com.novia.sanitymate.databinding.FragmentChooseBinding

class ChooseFragment : Fragment() {

    private var _binding: FragmentChooseBinding? = null
    private val binding get() = _binding!!

    private var doubleBackToExitPressedOnce = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpButton()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    requireActivity().finish()
                    return
                }

                doubleBackToExitPressedOnce = true
                requireView().postDelayed({ doubleBackToExitPressedOnce = false }, 2000) // Timeout for double press

                Snackbar.make(binding.root, "Press back again to exit!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.black))
                    .show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpButton() {
        binding.startButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_chooseFragment_to_formOneFragment)
        }
        binding.teamButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_chooseFragment_to_teamFragment)
        }
    }
}