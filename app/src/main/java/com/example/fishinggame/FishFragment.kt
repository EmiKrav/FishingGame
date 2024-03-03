package com.example.fishinggame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentFishBinding


class FishFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFishBinding.inflate(inflater)


        binding.textView3.text = "0.5 kg";
        binding.textView4.text = "2 €";

        val args = FishFragmentArgs.fromBundle(requireArguments())
        var sk = args.Pinigai;

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                FishFragmentDirections.actionFishFragmentToGameScreenFragment(sk + 2F)
            findNavController().navigate(action)
        }
        binding.button3
            .setOnClickListener {
                val action =
                    FishFragmentDirections.actionFishFragmentToGameScreenFragment(sk + 2F)
                findNavController().navigate(action)
            }

        return binding.root
    }

}