package com.example.fishinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentMainScreenBinding


class MainScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val binding = FragmentMainScreenBinding.inflate(inflater)
        val args = MainScreenFragmentArgs.fromBundle(requireArguments())
        val prog = args.Pinigai;
        var sk = args.Pinigai;

        binding.button
            .setOnClickListener {
                val action =
                    MainScreenFragmentDirections.actionMainScreenFragmentToGameScreenFragment(sk)
                findNavController().navigate(action)
            }



        return binding.root
    }
}