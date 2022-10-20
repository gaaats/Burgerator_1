package com.reciperartgen.burgeratorart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.burgeratorart.databinding.FragmentRandomBurgerBinding
import kotlin.random.Random


class RandomBurgerFragment : Fragment() {
    private var _binding: FragmentRandomBurgerBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    private val sectors = arrayOf(
        "Hamburger",
        "Cheeseburger",
        "Fishburger",
        "Freshburger",
        "Chickenburger"
    )
    private val sectorDegrees = arrayOf(1,1,1,1,1,1,1)
    private val singleSectorDegree = 360 / sectors.size
    private var isSpinning = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomBurgerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnImgExit.setOnClickListener {
                initAlertDialogExit()
            }

            calculateDegree()
            binding.btnSpin.setOnClickListener {
                if (!isSpinning) {
                    makeSpin()
                    isSpinning = true
                }
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

    private fun makeSpin() {
        val winNum = Random.nextInt(sectors.size - 1)

        //calculate number of degrees for set pointer to correct sector in UI
        val nisNeedAddRotate = (360 - winNum * singleSectorDegree).toFloat()
        val rotAnimation = RotateAnimation(
            0f,
            (360f * sectors.size) + nisNeedAddRotate,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        rotAnimation.apply {
            duration = 1000
            fillAfter = true
            interpolator = DecelerateInterpolator()
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    val userResult = sectors[winNum]
                    Toast.makeText(
                        requireContext(),
                        "Try to cook $userResult",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    isSpinning = false
                }
                override fun onAnimationRepeat(p0: Animation?) {
                }
            })
            binding.imgWheelElement.startAnimation(rotAnimation)
        }
    }

    private fun calculateDegree() {
        for (i in sectors.indices) {
            sectorDegrees[i] = (i + 1) * singleSectorDegree
        }
    }
}