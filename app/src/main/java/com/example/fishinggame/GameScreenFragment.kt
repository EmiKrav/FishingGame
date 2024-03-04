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

    var sk: Float = 0F
    var kiekis: Int = 0

    var Laikas: String =""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        if (savedInstanceState !=null) {

            sk = savedInstanceState.getFloat("Pinigai");
            kiekis = savedInstanceState.getInt("Kiekis");

            Laikas = savedInstanceState.getString("Laikas").toString();
        }
        else
        {
           val args = GameScreenFragmentArgs.fromBundle(requireArguments())
            sk = args.Pinigai;
            kiekis = args.Kiekis;
            Laikas = args.Laikas.toString();
        }

        val binding = FragmentGameScreenBinding.inflate(inflater)


        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                GameScreenFragmentDirections.actionGameScreenFragmentToMainScreenFragment(sk,kiekis,Laikas)
            findNavController().navigate(action)
        }


                binding.textView.text = "$sk";
                binding.textView2.text = "$kiekis";

                binding.textView6.text = "$Laikas"



         binding.imageView
           .setOnClickListener {
               val action =
                   GameScreenFragmentDirections.actionGameScreenFragmentToCathingFragment(sk,kiekis, Laikas)
               findNavController().navigate(action)
           }

        return binding.root
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


        outState.putFloat("Pinigai", sk);
        outState.putInt("Kiekis", kiekis);
        outState.putString("Laikas", Laikas)
    }

}
