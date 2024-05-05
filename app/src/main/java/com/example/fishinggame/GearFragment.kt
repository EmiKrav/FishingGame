package com.example.fishinggame

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.example.fishinggame.databinding.FragmentGearBinding
import com.example.fishinggame.databinding.FragmentTiltasBinding

class GearFragment : Fragment() {

    var current : Int = 0;
    var currentRite : Int = 0;
    var currentPlude : Int = 0;
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var ShopList: List<Shop> = listOf(
            Shop("Starter Rod", 0F,false,R.drawable.rod),
            Shop("Next Rod", 10F,true,R.drawable.rod2),
            Shop("Super Rod", 30F,true,R.drawable.rod3),
            Shop("Amazing Catcher", 100F,true,R.drawable.rod4),
            Shop("Expensive Stick", 1000F,true,R.drawable.rod5)
        )
        var ShopList2: List<Shop> = listOf(
            Shop("Starter Rite", 0F,false,R.drawable.rite),
            Shop("Cyan Rite", 20F,true,R.drawable.rite2)
        )
        var ShopList3: List<Shop> = listOf(
            Shop("Starter Plude", 0F,false,R.drawable.plude2),
            Shop("Next Plude", 2F,true,R.drawable.plude1)
        )
        //  current = savedInstanceState.getInt("Ezeras");
        //  view?.setBackgroundResource(ShopList[current].Image);
        //  }
        // else{
        val viewModel: DataStoreViewModel by viewModels()
        viewModel.getMeskeresPaveiksliukas.observe(viewLifecycleOwner) {
            current = it;

            val meskere = view?.findViewById<ImageView>(R.id.imageView7)
            meskere?.setImageResource(ShopList[current].Image)

        }
        // }
        viewModel.getRitesPaveiksliukas.observe(viewLifecycleOwner) {
            currentRite = it;

            val rite = view?.findViewById<ImageView>(R.id.imageView6)
            rite?.setImageResource(ShopList2[currentRite].Image)
        }
        viewModel.getPludesPaveiksliukas.observe(viewLifecycleOwner) {
            currentPlude = it;

            val plude = view?.findViewById<ImageView>(R.id.imageView8)
            plude?.setImageResource(ShopList3[currentPlude].Image)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var ShopList: List<Shop> = listOf(
            Shop("Starter Rod", 0F,false,R.drawable.rod),
            Shop("Next Rod", 10F,true,R.drawable.rod2),
            Shop("Super Rod", 30F,true,R.drawable.rod3),
            Shop("Amazing Catcher", 100F,true,R.drawable.rod4),
            Shop("Expensive Stick", 1000F,true,R.drawable.rod5)
        )
        var ShopList2: List<Shop> = listOf(
            Shop("Starter Rite", 0F,false,R.drawable.rite),
            Shop("Cyan Rite", 20F,true,R.drawable.rite2)
        )
        var ShopList3: List<Shop> = listOf(
            Shop("Starter Plude", 0F,false,R.drawable.plude2),
            Shop("Next Plude", 2F,true,R.drawable.plude1)
        )
        val binding = FragmentGearBinding.inflate(inflater)
        val viewModel: DataStoreViewModel by viewModels()

        var meskeres : String = ""
        viewModel.getMeskeres.observe(viewLifecycleOwner) {
            meskeres = it
        }
        var rites : String = ""
        viewModel.getRites.observe(viewLifecycleOwner) {
            rites = it
        }
        var pludes : String = ""
        viewModel.getPludes.observe(viewLifecycleOwner) {
            pludes = it
        }
        binding.imageView7.setOnClickListener {
            current++
            for (i  in ShopList.indices) {
                if ((current + i) == ShopList.lastIndex + 1) {
                    current = 0
                    binding.imageView7.setImageResource(ShopList[current].Image)
                    viewModel.saveMeskeresPaveiksliukas(current);
                    break
                }
                if (meskeres.contains(ShopList[current + i].Pavadinimas)) {
                    binding.imageView7.setImageResource(ShopList[current+ i].Image)
                    viewModel.saveMeskeresPaveiksliukas(current + i);
                    current += i;
                    break
                }
            }
        }
        binding.imageView6.setOnClickListener {
            currentRite++
            for (i  in ShopList2.indices) {
                if ((currentRite + i) == ShopList2.lastIndex + 1) {
                    currentRite = 0
                    binding.imageView6.setImageResource(ShopList2[currentRite].Image)
                    viewModel.saveRitesPaveiksliukas(currentRite);
                    break
                }
                if (rites.contains(ShopList2[currentRite + i].Pavadinimas)) {
                    binding.imageView6.setImageResource(ShopList2[currentRite+ i].Image)
                    viewModel.saveRitesPaveiksliukas(currentRite + i);
                    currentRite += i;
                    break
                }
            }
        }
        binding.imageView8.setOnClickListener {
            currentPlude++
            for (i  in ShopList3.indices) {
                if ((currentPlude + i) == ShopList3.lastIndex + 1) {
                    currentPlude = 0
                    binding.imageView8.setImageResource(ShopList3[currentPlude].Image)
                    viewModel.savePludesPaveiksliukas(currentPlude);
                    break
                }
                if (pludes.contains(ShopList3[currentPlude + i].Pavadinimas)) {
                    binding.imageView8.setImageResource(ShopList3[currentPlude+ i].Image)
                    viewModel.savePludesPaveiksliukas(currentPlude + i);
                    currentPlude += i;
                    break
                }
            }
        }

        return binding.root
    }

}