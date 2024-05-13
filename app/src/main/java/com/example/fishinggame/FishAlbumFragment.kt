package com.example.fishinggame

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishinggame.databinding.FragmentFishAlbumBinding
import com.example.fishinggame.databinding.FragmentMenuBinding
import java.net.URL
import java.util.concurrent.Executors


class FishAlbumFragment : Fragment() {

    var index = 0;
    var zuvys : String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFishAlbumBinding.inflate(inflater)

        var list = Fishes.getFishes()
        val executor = Executors.newSingleThreadExecutor()
        // Once the executor parses the URL
        // and receives the image, handler will load it
        // in the ImageView
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null

        val viewModel: DataStoreViewModel by viewModels()



        if (savedInstanceState !=null) {

            index = savedInstanceState.getInt("Indeksas")
            zuvys = savedInstanceState.getString("Zuvys").toString()

            binding.textView2.text = list[index].Pavadinimas

            if (zuvys.contains(list[index].Pavadinimas)) {
                binding.textView2.setTextColor(Color.parseColor("#009688"))
            } else {
                binding.textView2.setTextColor(Color.parseColor("#000000"))
            }

        }
        else {
            viewModel.getLaikai.observe(viewLifecycleOwner) {
                zuvys = it
            }
            binding.textView2.text = list[index].Pavadinimas

            if (zuvys.contains(list[index].Pavadinimas)) {
                binding.textView2.setTextColor(Color.parseColor("#009688"))
            } else {
                binding.textView2.setTextColor(Color.parseColor("#000000"))
            }
        }

            // Only for Background process (can take time depending on the Internet speed)
            executor.execute {
                val imageURL = list[index].url
                try {
                    val `in` = URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(`in`)
                    // Only for making changes in UI
                    handler.post {
                        val d: Drawable = BitmapDrawable(image)
                        binding.imageView10.setImageDrawable(d)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    handler.post {
                        binding.imageView10.setImageResource(R.drawable.nofish)
                    }
                }
            }
        binding.button8.setOnClickListener {
            if (index >= list.size-1){
                index = 0
            }
            else {
                index++
            }
            executor.execute {
                val imageURL = list[index].url
                try {
                    val `in` = URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(`in`)
                    // Only for making changes in UI
                    handler.post {
                        val d: Drawable = BitmapDrawable(image)
                        binding.imageView10.setImageDrawable(d)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    handler.post {
                        binding.imageView10.setImageResource(R.drawable.nofish)
                    }
                }
            }

            binding.textView2.text = list[index].Pavadinimas
            if (zuvys.contains(list[index].Pavadinimas)) {
                binding.textView2.setTextColor(Color.parseColor("#009688"))
            }
            else{
                binding.textView2.setTextColor(Color.parseColor("#000000"))
            }
        }
        binding.button9.setOnClickListener {
            if (index <= 0){
                index = list.size-1
            }
            else {
                index--
            }
            executor.execute {
                val imageURL = list[index].url
                try {
                    val `in` = URL(imageURL).openStream()
                    image = BitmapFactory.decodeStream(`in`)
                    // Only for making changes in UI
                    handler.post {
                        val d: Drawable = BitmapDrawable(image)
                        binding.imageView10.setImageDrawable(d)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    handler.post {
                        binding.imageView10.setImageResource(R.drawable.nofish)
                    }
                }
            }

            binding.textView2.text = list[index].Pavadinimas
            if (zuvys.contains(list[index].Pavadinimas)) {
                binding.textView2.setTextColor(Color.parseColor("#009688"))
            }
            else{
                binding.textView2.setTextColor(Color.parseColor("#000000"))
            }

        }

        return binding.root
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Indeksas",index)
        outState.putString("Zuvys",zuvys)
    }
}