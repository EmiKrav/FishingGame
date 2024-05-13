package com.example.fishinggame

import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        var sk = Music.CurrentTR()
        if (sk != 1){
            Music.stopMusic()
            context?.let { Music.CreateMusic(it) }
            Music.playMusic()
        }


        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                MenuFragmentDirections.actionMenuFragmentToMainScreenFragment()
            findNavController().navigate(action)
        }

        binding.button4
            .setOnClickListener {
                Music.stopMusic()
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
                Music.stopMusic()

                activity?.finish();
                exitProcess(0);
        }
        binding.button7
            .setOnClickListener {
                val action =
                    MenuFragmentDirections.actionMenuFragmentToShopFragment()
                findNavController().navigate(action)
            }
        binding.button10
            .setOnClickListener {
                val action =
                    MenuFragmentDirections.actionMenuFragmentToFishAlbumFragment()
                findNavController().navigate(action)
            }
        return binding.root
    }

}
