package com.reciperartgen.burgeratorart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.burgeratorart.databinding.FragmentSingleInfoBurgerBinding

class SingleInfoBurgerFragment : Fragment() {

    private val args: SingleInfoBurgerFragmentArgs by navArgs()

    private var _binding: FragmentSingleInfoBurgerBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleInfoBurgerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            val recipe = args.singleRecipeSend
            binding.tvTitleRecipe.text = recipe._title
            binding.tvRecipeIngridients.text = recipe._ingredients
            binding.tvRecipeInstructions.text = recipe._instructions
            initAllBtnsOnScreen()
            super.onViewCreated(view, savedInstanceState)

            binding.btnImgExit.setOnClickListener {
                initAlertDialogExit()
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

    private fun initAllBtnsOnScreen() {
        binding.btnImgExit.setOnClickListener {
            goBackPressed()
        }
        binding.btnOk.setOnClickListener {
            goBackPressed()
        }
    }

    private fun goBackPressed() {
        requireActivity().onBackPressed()
    }
}