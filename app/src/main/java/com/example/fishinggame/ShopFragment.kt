package com.example.fishinggame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishinggame.databinding.FragmentShopBinding


class ShopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShopBinding.inflate(inflater)

        val viewModel: DataStoreViewModel by viewModels()

        viewModel.getPinigai.observe(viewLifecycleOwner) {
            val p = String.format("%.2f", it)
            binding.textView7.text = "Money: $p €";
        }

        var ShopList: List<Shop> = listOf(
            Shop("Beginer's Bridge", 0F,false,R.drawable.tiltas),
            Shop("Mountains Lake", 100F,true,R.drawable.lake1),
            Shop("Green Place", 10F,true,R.drawable.lake2)
        )


        val recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = ShopAdapter(viewLifecycleOwner,viewModel, ShopList)

        val ItemAdapter = recycler.adapter



        return binding.root
    }

}