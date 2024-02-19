package com.example.fishinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentGameScreenBinding

class GameScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameScreenBinding.inflate(inflater)
         binding.imageView
           .setOnClickListener {
             findNavController()
               .navigate(R.id.action_gameScreenFragment_to_cathingFragment)
         }

        return binding.root
    }

}