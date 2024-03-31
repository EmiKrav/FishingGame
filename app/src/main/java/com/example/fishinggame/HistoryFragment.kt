package com.example.fishinggame

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fishinggame.databinding.FragmentGameScreenBinding
import com.example.fishinggame.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    var sk: Float = 0F
    var kiekis: Int = 0

    var Laikas: String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val binding = FragmentHistoryBinding.inflate(inflater)

        val viewModel: DataStoreViewModel by viewModels()

        viewModel.getPinigai.observe(viewLifecycleOwner) {
            binding.Pinigai.text = "Pinigai: $it";
        }
        viewModel.getKiekis.observe(viewLifecycleOwner) {

            binding.Kiekis.text = "Kiekis: $it";
        }

        viewModel.getLaikai.observe(viewLifecycleOwner) {

            Laikas = it;
        }



//        binding.textView.text = "Pinigai: "+ System.lineSeparator() + "$sk";
//        binding.textView2.text = "Kiekis: "+ System.lineSeparator() + "$kiekis";
//
//        binding.textView6.text = "$Laikas"




//        sk += args.Pinigai;
//        kiekis += args.Kiekis;
//        Laikas += args.Laikas.toString();




        var HistoryList: MutableList<History> = mutableListOf(
            History(Laikas)
        )

         val recycler = binding.Recy
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = HistoryAdapter(HistoryList)


        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                HistoryFragmentDirections.actionHistoryFragmentToMenuFragment()
            findNavController().navigate(action)
        }


        return binding.root
    }

}
