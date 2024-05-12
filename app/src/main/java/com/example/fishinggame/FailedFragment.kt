package com.example.fishinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentFailedBinding
import com.example.fishinggame.databinding.FragmentMainScreenBinding

class FailedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        val binding = FragmentFailedBinding.inflate(inflater)
        var muzsk = Music.CurrentTR()
        if (muzsk != 4){
            context?.let { Music.CreateMusic4(it) }
            Music.playMusic()
        }

        binding.button3
            .setOnClickListener {
                val action =
                    FailedFragmentDirections.actionFailedFragmentToGameScreenFragment()
                findNavController().navigate(action)
            }
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

            val action =
                FailedFragmentDirections.actionFailedFragmentToGameScreenFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}