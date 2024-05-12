package com.example.fishinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fishinggame.databinding.FragmentFishAlbumBinding
import com.example.fishinggame.databinding.FragmentMenuBinding


class FishAlbumFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFishAlbumBinding.inflate(inflater)

        return binding.root
    }

}