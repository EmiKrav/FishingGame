package com.example.fishinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentMainScreenBinding


class MainScreenFragment : Fragment() {

    var sk: Float = 0F
    var kiekis: Int = 0
    var Laikas: String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        val binding = FragmentMainScreenBinding.inflate(inflater)

        binding.button
            .setOnClickListener {
                val action =
                    MainScreenFragmentDirections.actionMainScreenFragmentToMenuFragment()
                findNavController().navigate(action)
            }



        return binding.root
    }
}