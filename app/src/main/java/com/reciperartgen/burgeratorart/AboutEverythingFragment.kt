package com.reciperartgen.burgeratorart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.burgeratorart.databinding.FragmentAboutEverythingBinding


class AboutEverythingFragment : Fragment() {

    private var _binding: FragmentAboutEverythingBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutEverythingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnImgExit.setOnClickListener {
                initAlertDialogExit()
            }
            binding.btnVhyItIsGood.setOnClickListener {
                findNavController().navigate(R.id.action_aboutEverythingFragment_to_vhyItIsGoogFragment)
            }
            binding.btnNativeCountry.setOnClickListener {
                findNavController().navigate(R.id.action_aboutEverythingFragment_to_nativeCountryFragment)
            }
            binding.btnFirstTimeCreated.setOnClickListener {
                findNavController().navigate(R.id.action_aboutEverythingFragment_to_firstTimeCreatedFragment)
            }
            binding.btnPhotos.setOnClickListener {
                findNavController().navigate(R.id.action_aboutEverythingFragment_to_photoGalleryFragment)
            }
            binding.btnOtherFacts.setOnClickListener {
                findNavController().navigate(R.id.action_aboutEverythingFragment_to_otherFactsFragment)
            }
            binding.btnAboutApp.setOnClickListener {
                findNavController().navigate(R.id.action_aboutEverythingFragment_to_aboutAppFragment)
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