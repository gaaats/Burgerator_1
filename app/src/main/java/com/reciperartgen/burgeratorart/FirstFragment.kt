package com.reciperartgen.burgeratorart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.burgeratorart.databinding.FragmentFirstBinding
import com.reciperartgen.burgeratorart.databinding.FragmentVhyItIsGoogBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnSettings.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_settingsFragment)
            }
            binding.btnFavorites.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_likedBurgersFragment)
            }
            binding.imgFavorites.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_likedBurgersFragment)
            }
            binding.btnHeart.setOnClickListener {
                Snackbar.make(binding.root, "I love you too ♥♥♥", Snackbar.LENGTH_LONG).show()
            }
            binding.btnAllRecipes.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_chooseBurderFragment)
            }
            binding.imgToRecipes.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_chooseBurderFragment)
            }
            binding.btnAboutBurger.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_aboutEverythingFragment)
            }
            binding.imgAboutEverything.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_aboutEverythingFragment)
            }


        } catch (e: Exception) {
            initError()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun initAlertDialogExit() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you definitely want to log out, the current data will not be saved?")
            .setPositiveButton("Yes, Exit") { _, _ ->
                requireActivity().onBackPressed()
            }
            .setNegativeButton("Deny") { _, _ ->
            }
            .setCancelable(true)
            .create()
            .show()
    }



}