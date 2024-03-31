package com.example.fishinggame

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentGameScreenBinding

class GameScreenFragment : Fragment() {

    var sk: Float = 0F
    var kiekis: Int = 0

    var Laikas: String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentGameScreenBinding.inflate(inflater)


        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                GameScreenFragmentDirections.actionGameScreenFragmentToMenuFragment()
            findNavController().navigate(action)
        }



         binding.imageView
           .setOnClickListener {
               val action =
                   GameScreenFragmentDirections.actionGameScreenFragmentToCathingFragment()
               findNavController().navigate(action)
           }

        return binding.root
    }
}
