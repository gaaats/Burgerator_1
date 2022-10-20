package com.reciperartgen.burgeratorart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.burgeratorart.databinding.FragmentListBurgersBinding
import com.reciperartgen.burgeratorart.entitys.MyInterceptor
import com.reciperartgen.burgeratorart.entitys.RecipeAPINinja
import com.reciperartgen.burgeratorart.entitys.ResponseApiSingleRecipe
import com.reciperartgen.burgeratorart.recyclerviev.RecipeAdapterList
import com.reciperartgen.burgeratorart.utils.FavoriteRecipesVievmodel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListBurgersFragment : Fragment() {

    private var list = mutableListOf<ResponseApiSingleRecipe>()

    private val adapter by lazy {
        RecipeAdapterList()
    }

    private val mainVievModel by activityViewModels<FavoriteRecipesVievmodel>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RecipeAPINinja.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: RecipeAPINinja by lazy {
        retrofit.create(RecipeAPINinja::class.java)
    }

    private var _binding: FragmentListBurgersBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBurgersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            addDividers()
            startProgressAndLoad()
            initOnItemClickListener()
            initClickListenerAddFavorite()


            binding.btnImgExit.setOnClickListener {
                initAlertDialogExit()
            }
            binding.btnGoToFavorites.setOnClickListener {
                findNavController().navigate(R.id.action_listBurgersFragment_to_likedBurgersFragment)
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


    private fun initOnItemClickListener() {
        adapter.setOnItemClickListener {
            ListBurgersFragmentDirections.actionListBurgersFragmentToSingleInfoBurgerFragment(singleRecipeSend = it).also {
                findNavController().navigate(it)
            }
        }
    }

    private fun initClickListenerAddFavorite() {
        adapter.setOnItemClickListenerHeart {
            mainVievModel.addToShopCart(it)
        }
    }

    private fun saveToClipBoard() {
        Snackbar.make(binding.root, "Saved!", Snackbar.LENGTH_LONG).show()
    }

    private fun addDividers() {
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
    private fun startProgressAndLoad() {
        lifecycleScope.launch {
            binding.imgMain.visibility = View.GONE
            binding.btnGoToFavorites.visibility = View.GONE
            binding.tvHelperName.visibility = View.GONE
            binding.tvHelperPhoto.visibility = View.GONE
            binding.cardV.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE
            startLoadList()
            delay(3000)
            binding.lottieAnimVaiting.visibility = View.VISIBLE
            binding.tvPleaseVaitLoading.visibility = View.VISIBLE

            binding.imgMain.visibility = View.VISIBLE
            binding.cardV.visibility = View.VISIBLE
            binding.tvHelperName.visibility = View.VISIBLE
            binding.tvHelperPhoto.visibility = View.VISIBLE
            binding.btnGoToFavorites.visibility = View.VISIBLE
            binding.btnImgExit.visibility = View.VISIBLE
            binding.lottieAnimVaiting.visibility = View.GONE
            binding.tvPleaseVaitLoading.visibility = View.GONE
        }
    }

    private fun startLoadList() {
        lifecycleScope.launch {
            try {
                val result = api.getRecipes()
                if (result.isSuccessful) {
                    list = result.body()!!
                    adapter.submitList(list)
                    binding.recyclerView.adapter = adapter
                } else {
                    initError()
                }
            } catch (e: Exception) {
                initError()
            }
        }
    }
}