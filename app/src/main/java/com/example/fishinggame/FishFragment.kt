package com.example.fishinggame

import android.R.attr.bitmap
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentFishBinding
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.Executors
import kotlin.random.Random


class FishFragment : Fragment() {


    var sk: Float = 0.0F;
    var kiekis: Int = 0
    var Laikas: String = ""
    var dd: Float = 0F
    var kk: Float = 0F
    var pavadinimas: String = ""
    var Url: String = ""

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFishBinding.inflate(inflater)

        var muzsk = Music.CurrentTR()
        if (muzsk != 3){
            context?.let { Music.CreateMusic3(it) }
            Music.playMusic()
        }
        val viewModel: DataStoreViewModel by viewModels()



        if (savedInstanceState !=null) {
            pavadinimas = savedInstanceState.getString("Pavadinimas").toString();
            sk = savedInstanceState.getFloat("Pinigai")
            dd = savedInstanceState.getFloat("Kaina");
            kiekis = savedInstanceState.getInt("Kiekis")
            Laikas = savedInstanceState.getString("Laikas").toString()
            kk = savedInstanceState.getFloat("Kg")
            Url = savedInstanceState.getString("Url").toString()
            // Declaring executor to parse the URL
            val executor = Executors.newSingleThreadExecutor()
            // Once the executor parses the URL
            // and receives the image, handler will load it
            // in the ImageView
            val handler = Handler(Looper.getMainLooper())
            var image: Bitmap? = null
            // Only for Background process (can take time depending on the Internet speed)
            executor.execute {
                val imageURL = Url
                try {
                    val `in` = URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(`in`)
                    // Only for making changes in UI
                    handler.post {
                        val d: Drawable = BitmapDrawable(image)
                        binding.imageView3.setImageDrawable(d)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    handler.post {
                        binding.imageView3.setImageResource(R.drawable.nofish)
                    }
                }
            }


        }

        else{
            var list = Fishes.getFishes()
            val randomIndex = Random.nextInt(list.size);
            val randomElement = list[randomIndex]

            pavadinimas = randomElement.Pavadinimas
            Url = randomElement.url

            // Declaring executor to parse the URL
            val executor = Executors.newSingleThreadExecutor()
            // Once the executor parses the URL
            // and receives the image, handler will load it
            // in the ImageView
            val handler = Handler(Looper.getMainLooper())
            var image: Bitmap? = null
            // Only for Background process (can take time depending on the Internet speed)
            executor.execute {
                val imageURL = Url
                try {
                    val `in` = URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(`in`)
                    // Only for making changes in UI

                    handler.post {
                        val d: Drawable = BitmapDrawable(image)
                        binding.imageView3.setImageDrawable(d)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    handler.post {
                        binding.imageView3.setImageResource(R.drawable.nofish)
                    }
                }
            }

           // Toast.makeText(activity,binding.imageView3.drawable.toString(),Toast.LENGTH_SHORT).show();


            viewModel.getPinigai.observe(viewLifecycleOwner) {
                sk = it;
            }

            viewModel.getKiekis.observe(viewLifecycleOwner) {
                kiekis = it;
            }
            viewModel.getLaikai.observe(viewLifecycleOwner) {
                Laikas = it;
            }


            dd = Random.nextFloat() * (100F - 0.1F) + 0.01F
            dd = String.format("%.2f", dd).toFloat()

            kk = Random.nextFloat() * (100F - 0.010F) + 0.010F
            kk = String.format("%.3f", kk).toFloat()


        }
        binding.textView5.text = pavadinimas;
        binding.textView3.text = "$kk kg";
        binding.textView4.text = "$dd €";

      //  viewModel.savePinigai(0F);
        //  viewModel.saveKiekis(0);
          // viewModel.saveLaikai("");
      //  viewModel.saveEzerai("");
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.savePinigai(sk + dd);
            viewModel.saveKiekis(kiekis + 1);
            viewModel.saveLaikai(Laikas +  System.lineSeparator()+ LocalDateTime.now().
            format(DateTimeFormatter.ofPattern("yyyy:MM:dd - HH:mm:ss")).toString() +
                    System.lineSeparator() + pavadinimas + "      " + kk +" kg");

            val action =
                FishFragmentDirections.actionFishFragmentToGameScreenFragment()
            findNavController().navigate(action)
        }
        binding.button3
            .setOnClickListener {
                viewModel.savePinigai(sk + dd);
                viewModel.saveKiekis(kiekis + 1);
                viewModel.saveLaikai(Laikas +  System.lineSeparator()+ LocalDateTime.now().
                format(DateTimeFormatter.ofPattern("yyyy:MM:dd - HH:mm:ss")).toString() +
                        System.lineSeparator() + pavadinimas + "      " + kk+" kg");

                val action =
                    FishFragmentDirections.actionFishFragmentToGameScreenFragment()
                findNavController().navigate(action)
            }

        return binding.root
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putFloat("Pinigai", sk);
        outState.putFloat("Kaina", dd);
        outState.putInt("Kiekis", kiekis);
        outState.putString("Laikas",Laikas)
        outState.putFloat("Kg", kk);
        outState.putString("Pavadinimas", pavadinimas)
        outState.putString("Url", Url)
    }


}

