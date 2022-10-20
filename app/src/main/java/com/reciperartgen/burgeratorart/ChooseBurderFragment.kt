package com.reciperartgen.burgeratorart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.burgeratorart.databinding.FragmentChooseBurderBinding
import com.reciperartgen.burgeratorart.databinding.FragmentVhyItIsGoogBinding

class ChooseBurderFragment : Fragment() {

    private var _binding: FragmentChooseBurderBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseBurderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnSettings.setOnClickListener {
                findNavController().navigate(R.id.action_chooseBurderFragment_to_settingsFragment)
            }
            binding.btnHeart.setOnClickListener {
                findNavController().navigate(R.id.action_chooseBurderFragment_to_likedBurgersFragment)
            }
            binding.btnGetAllRecipes.setOnClickListener {
                findNavController().navigate(R.id.action_chooseBurderFragment_to_listBurgersFragment)
            }
            binding.imgListRecipes.setOnClickListener {
                findNavController().navigate(R.id.action_chooseBurderFragment_to_listBurgersFragment)
            }
            binding.btnGetAllRecipes.setOnClickListener {
                findNavController().navigate(R.id.action_chooseBurderFragment_to_listBurgersFragment)
            }
            binding.btnRandomRecipe.setOnClickListener {
                findNavController().navigate(R.id.action_chooseBurderFragment_to_randomBurgerFragment)
            }
            binding.imgRandomRecipe.setOnClickListener {
                findNavController().navigate(R.id.action_chooseBurderFragment_to_randomBurgerFragment)
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