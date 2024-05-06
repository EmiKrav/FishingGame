package com.example.fishinggame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentMainScreenBinding
import kotlin.system.exitProcess


class MainScreenFragment : Fragment() {

    var sk: Float = 0F
    var kiekis: Int = 0
    var Laikas: String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val binding = FragmentMainScreenBinding.inflate(inflater)

        var sk = Music.CurrentTR()
        if (sk != 1){
            context?.let { Music.CreateMusic(it) }
            Music.playMusic()
        }

//Music.playMusic(false)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            activity?.finish();
            exitProcess(0);
        }
        binding.button
            .setOnClickListener {

                        val action =
                            MainScreenFragmentDirections.actionMainScreenFragmentToMenuFragment()
                        findNavController().navigate(action)
            }



        return binding.root
    }


}