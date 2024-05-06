package com.example.fishinggame

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentTiltasBinding
import kotlinx.coroutines.flow.callbackFlow


class TiltasFragment : Fragment() {

   var kibimas = false
   var waitTimer : CountDownTimer? = null
    var anim : ObjectAnimator? = null

    var t1 : CountDownTimer? = null
    var current : Int = 0;
    var currentMeskere : Int = 0;
    var currentPlude : Int = 0;
    override fun onActivityCreated(savedInstanceState: Bundle?) {


        super.onActivityCreated(savedInstanceState)
        var ShopList = Data.getPlaces()
        var ShopList2 = Data.getRods()
        var ShopList3 = Data.getFloats()
          //  current = savedInstanceState.getInt("Ezeras");
          //  view?.setBackgroundResource(ShopList[current].Image);
      //  }
       // else{
            val viewModel: DataStoreViewModel by viewModels()
            viewModel.getPaveiksliukas.observe(viewLifecycleOwner) {
                current = it;
                view?.setBackgroundResource(ShopList[current].Image);
            }
        viewModel.getMeskeresPaveiksliukas.observe(viewLifecycleOwner) {
            currentMeskere = it;

            val meskere = view?.findViewById<ImageView>(R.id.imageView)
            meskere?.setImageResource(ShopList2[currentMeskere].Image)

        }
        viewModel.getPludesPaveiksliukas.observe(viewLifecycleOwner) {
            currentPlude = it;

            val plude = view?.findViewById<ImageView>(R.id.imageView4)
            plude?.setImageResource(ShopList3[currentPlude].Image)

        }
       // }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var ShopList = Data.getPlaces()
        kibimas = false
        val binding = FragmentTiltasBinding.inflate(inflater)
        val viewModel: DataStoreViewModel by viewModels()

        var sk = Music.CurrentTR()
        if (sk != 2){
            context?.let { Music.CreateMusic2(it) }
            Music.playMusic()
        }

       requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                   val action =
                       TiltasFragmentDirections.actionGameScreenFragmentToMenuFragment()
                   findNavController().navigate(action)

       }


        val rnds = (3000..10000).random()
       var laikokoluzkibs = rnds.toLong();
        var ezerai : String = ""
        viewModel.getEzerai.observe(viewLifecycleOwner) {
            ezerai = it
        }



        timeris(binding, laikokoluzkibs);
        binding.imageButton
            .setOnClickListener {
                    current++
                for (i  in ShopList.indices) {
                    if ((current + i) == ShopList.lastIndex + 1) {
                        current = 0
                        view?.setBackgroundResource(ShopList[current].Image);
                        viewModel.savePaveiksliukas(current);
                        if(waitTimer != null) {
                            waitTimer!!.cancel();
                            waitTimer = null;
                        }
                        if(anim != null) {
                            anim!!.cancel();
                            anim = null;
                        }
                        if(t1 != null) {
                            t1!!.cancel();
                            t1 = null;
                        }
                        ObjectAnimator.ofFloat(binding.imageView4, "translationX", 0F).apply {
                            duration = 0
                            start()
                        }
                        timeris(binding, laikokoluzkibs);
                        break
                    }
                    if (ezerai.contains(ShopList[current + i].Pavadinimas)) {
                        view?.setBackgroundResource(ShopList[current + i].Image);
                        viewModel.savePaveiksliukas(current + i);
                        current += i;
                        if(waitTimer != null) {
                            waitTimer!!.cancel();
                            waitTimer = null;
                        }
                        if(anim != null) {
                            anim!!.cancel();
                            anim = null;
                        }
                        if(t1 != null) {
                            t1!!.cancel();
                            t1 = null;
                        }
                        ObjectAnimator.ofFloat(binding.imageView4, "translationX", 0F).apply {
                            duration = 0
                            start()
                        }
                        timeris(binding, laikokoluzkibs);
                        break
                    }
                }
            }
        binding.imageView5
            .setOnClickListener {

                    if(waitTimer != null) {
                        waitTimer!!.cancel();
                        waitTimer = null;
                    }
                    if(anim != null) {
                        anim!!.cancel();
                        anim = null;
                    }
                    if(t1 != null) {
                        t1!!.cancel();
                        t1 = null;
                    }
                    val action =
                        TiltasFragmentDirections.actionGameScreenFragmentToGearFragment()
                    findNavController().navigate(action)
                }

        binding.imageView
           .setOnClickListener {
               if (kibimas) {
                   kibimas = false
                   if(waitTimer != null) {
                       waitTimer!!.cancel();
                       waitTimer = null;
                   }
                   if(anim != null) {
                       anim!!.cancel();
                       anim = null;
                   }
                   if(t1 != null) {
                       t1!!.cancel();
                       t1 = null;
                   }
                   Music.pauseMusic()
                   val action =
                       TiltasFragmentDirections.actionGameScreenFragmentToCathingFragment()
                   findNavController().navigate(action)

               }
               else{
                   binding.textView.text = "Too early!"
                   kibimas = false
                   if(waitTimer != null) {
                       waitTimer!!.cancel();
                       waitTimer = null;
                   }
                   if(anim != null) {
                       anim!!.cancel();
                       anim = null;
                   }
                   if(t1 != null) {
                       t1!!.cancel();
                       t1 = null;
                   }
                   ObjectAnimator.ofFloat(binding.imageView4, "translationX", 0F).apply {
                       duration = 0
                       start()
                   }
                   timeris(binding, laikokoluzkibs)
               }

           }

        return binding.root
    }
    @SuppressLint("SuspiciousIndentation")

    fun animacija(binding: FragmentTiltasBinding, pozicija: Float){
        var nauja = pozicija
        val dur = 3000

        t1 = object : CountDownTimer(dur.toLong()+3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished < (dur.toLong()+3000)) {
                    kibimas = true;
                }
                if (millisUntilFinished > 2000 ) {
                    anim = ObjectAnimator.ofFloat(binding.imageView4, "translationX", nauja).apply {
                        duration = 1000.toLong()
                        start()
                    }
                    nauja -= 2 * nauja
                }
            }
            override fun onFinish() {
                if(anim != null) {
                    anim!!.cancel();
                    anim = null;
                }
                kibimas = false;
                binding.textView.text = "Too late!"
                ObjectAnimator.ofFloat(binding.imageView4, "translationX", 0F).apply {
                    duration = 0
                    start()
                }
                val rnds = (3000..10000).random()
                var laikokoluzkibs = rnds.toLong();

                timeris(binding, laikokoluzkibs);
            }
        }.start()
    }
    fun timeris (binding: FragmentTiltasBinding, laikas: Long){
        if(t1 != null) {
            t1!!.cancel();
            t1 = null;
        }
       waitTimer =  object : CountDownTimer(laikas, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (millisUntilFinished < (laikas - 1000.toLong())) {
                    binding.textView.text = ""
                }
            }
            override fun onFinish() {
                kibimas = false
                val rnds = (-3000..3000).random()
                var poz = rnds.toFloat();
                animacija(binding, poz)
            }
        }.start()
    }


}
