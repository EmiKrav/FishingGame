package com.example.fishinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentGameScreenBinding

class GameScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameScreenBinding.inflate(inflater)

        val args = GameScreenFragmentArgs.fromBundle(requireArguments())
        val prog = args.Pinigai;
        var sk = args.Pinigai;



        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                GameScreenFragmentDirections.actionGameScreenFragmentToMainScreenFragment(sk)
            findNavController().navigate(action)
        }


                binding.textView.text = "${args.Pinigai}";



         binding.imageView
           .setOnClickListener {
               val action =
                   GameScreenFragmentDirections.actionGameScreenFragmentToCathingFragment(sk)
               findNavController().navigate(action)
           }

        return binding.root
    }

}
