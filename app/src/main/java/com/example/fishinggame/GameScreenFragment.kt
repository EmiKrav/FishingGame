package com.example.fishinggame

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentGameScreenBinding

class GameScreenFragment : Fragment() {

   var kibimas = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentGameScreenBinding.inflate(inflater)


        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action =
                GameScreenFragmentDirections.actionGameScreenFragmentToMenuFragment()
            findNavController().navigate(action)
        }


        val rnds = (1000..10000).random()
       var laikokoluzkibs = rnds.toLong();

        timeris(binding, laikokoluzkibs);

        binding.imageView
           .setOnClickListener {
               if (kibimas) {
                   val action =
                       GameScreenFragmentDirections.actionGameScreenFragmentToCathingFragment()
                   findNavController().navigate(action)
                   kibimas = false
               }
               else{
                   timeris(binding, laikokoluzkibs)
               }

           }

        return binding.root
    }
    fun animacija( binding: FragmentGameScreenBinding, pozicija: Float){
        val dur = 3000
           ObjectAnimator.ofFloat(binding.imageView4, "translationX", pozicija).apply {
                duration = dur.toLong()
                start()
            }

        object : CountDownTimer(dur.toLong()+2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {

                kibimas = false;
                ObjectAnimator.ofFloat(binding.imageView4, "translationX", 0F).apply {
                    duration = 0
                    start()
                }
                val rnds = (1000..10000).random()
                var laikokoluzkibs = rnds.toLong();

                timeris(binding, laikokoluzkibs);

            }
        }.start()
    }
    fun timeris (binding: FragmentGameScreenBinding, laikas: Long){
        object : CountDownTimer(laikas, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                kibimas = true;

                val rnds = (-10000..10000).random()
                var poz = rnds.toFloat();
                animacija(binding, poz)
            }
        }.start()
    }
}
