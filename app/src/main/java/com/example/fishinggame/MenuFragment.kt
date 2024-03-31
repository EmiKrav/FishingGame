package com.example.fishinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentGameScreenBinding
import com.example.fishinggame.databinding.FragmentMenuBinding
import kotlin.system.exitProcess

class MenuFragment : Fragment() {
    var sk: Float = 0F
    var kiekis: Int = 0

    var Laikas: String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMenuBinding.inflate(inflater)


        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                MenuFragmentDirections.actionMenuFragmentToMainScreenFragment()
            findNavController().navigate(action)
        }

        binding.button4
            .setOnClickListener {
                val action =
                    MenuFragmentDirections.actionMenuFragmentToGameScreenFragment()
                findNavController().navigate(action)
            }
        binding.button6
            .setOnClickListener {
                val action =
                    MenuFragmentDirections.actionMenuFragmentToHistoryFragment()
                findNavController().navigate(action)
            }
        binding.button5
            .setOnClickListener {
                activity?.finish();
                exitProcess(0);
        }

        return binding.root
    }

}
