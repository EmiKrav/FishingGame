package com.example.fishinggame

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentFishBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FishFragment : Fragment() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var sk: Float = 0F
        var kiekis: Int = 0
        var Laikas: String =""

        val binding = FragmentFishBinding.inflate(inflater)


        val viewModel: DataStoreViewModel by viewModels()

//        if (savedInstanceState !=null) {
//
//            sk = savedInstanceState.getFloat("Pinigai");
//            kiekis = savedInstanceState.getInt("Kiekis");
//            Laikas = savedInstanceState.getString("Laikas").toString();
//            val TAG = "Rot";
//            Log.v(TAG,sk.toString());
//        }
//        else{
//            viewModel.getPinigai.observe(viewLifecycleOwner) {
//                sk = it;
//            }
//            viewModel.getKiekis.observe(viewLifecycleOwner) {
//                kiekis = it;
//            }
//            viewModel.getLaikai.observe(viewLifecycleOwner) {
//                Laikas = it;
//            }
//            sk += 2;
//            kiekis += 1;
//            Laikas += System.lineSeparator()+LocalDateTime.now().
//            format(DateTimeFormatter.ofPattern("yyyy:MM:dd - HH:mm:ss")).toString();
//        }
        viewModel.getPinigai.observe(viewLifecycleOwner) {
            sk = it;
        }

        viewModel.getKiekis.observe(viewLifecycleOwner) {
            kiekis = it;
        }
        viewModel.getLaikai.observe(viewLifecycleOwner) {
            Laikas = it;
        }
        sk += 2F;
        kiekis += 1;
        Laikas += System.lineSeparator()+ LocalDateTime.now().
        format(DateTimeFormatter.ofPattern("yyyy:MM:dd - HH:mm:ss")).toString();
        viewModel.savePinigai(sk);
        viewModel.saveKiekis(kiekis);
        viewModel.saveLaikai(Laikas);


        binding.textView5.text = "Žuvis";
        binding.textView3.text = "0.5 kg";
        viewModel.getPinigai.observe(viewLifecycleOwner) {
            binding.textView4.text = "$it"
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

            val action =
                FishFragmentDirections.actionFishFragmentToGameScreenFragment()
            findNavController().navigate(action)
        }
        binding.button3
            .setOnClickListener {
                val action =
                    FishFragmentDirections.actionFishFragmentToGameScreenFragment()
                findNavController().navigate(action)
            }

        return binding.root
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//
//        outState.putFloat("Pinigai",sk);
//        outState.putInt("Kiekis", kiekis);
//
//        outState.putString("Laikas", Laikas)
//    }



}
