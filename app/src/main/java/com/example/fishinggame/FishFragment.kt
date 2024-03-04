package com.example.fishinggame

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentFishBinding
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.minutes


class FishFragment : Fragment() {

    var sk: Float = 0F
    var kiekis: Int = 0
    var Laikas: String =""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        if (savedInstanceState !=null) {
            sk = savedInstanceState.getFloat("Pinigai");
            kiekis = savedInstanceState.getInt("Kiekis");
            Laikas = savedInstanceState.getString("Laikas").toString();
        }
        else{
            val args = FishFragmentArgs.fromBundle(requireArguments())
            sk = args.Pinigai;
            kiekis = args.Kiekis;
            Laikas = args.Laikas.toString();
        }


            val binding = FragmentFishBinding.inflate(inflater)

        binding.textView5.text = "Žuvis";
        binding.textView3.text = "0.5 kg";
        binding.textView4.text = "2 €";


        Laikas += System.lineSeparator()+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                FishFragmentDirections.actionFishFragmentToGameScreenFragment(sk+2,kiekis+1,Laikas
                )
            findNavController().navigate(action)
        }
        binding.button3
            .setOnClickListener {
                val action =
                    FishFragmentDirections.actionFishFragmentToGameScreenFragment(sk+2,kiekis+1, Laikas
                    )
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
