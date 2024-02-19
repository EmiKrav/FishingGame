package com.example.fishinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentCathingBinding
import com.example.fishinggame.databinding.FragmentGameScreenBinding


class CathingFragment : Fragment() {

    override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCathingBinding.inflate(inflater)

        return binding.root
    }

}