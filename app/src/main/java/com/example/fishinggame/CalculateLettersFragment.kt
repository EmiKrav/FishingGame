package com.example.fishinggame

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.fishinggame.databinding.FragmentCalculateLettersBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CalculateLettersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCalculateLettersBinding.inflate(inflater)

        binding.button.setOnClickListener {
            binding.cl1.removeViews(3,binding.cl1.childCount-3)
            binding.textView2.text = binding.editTextText.text.length.toString()
            for (i in binding.editTextText.text.indices) {
                var pcc = EgzoSkritulys(requireContext())


                val result = Bitmap.createBitmap(
                    binding.root.width,
                    binding.root.height,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(result)

                pcc.draw(canvas)

                pcc.layoutParams =
                    ConstraintLayout.LayoutParams( ConstraintLayout.LayoutParams.WRAP_CONTENT,  ConstraintLayout.LayoutParams.WRAP_CONTENT)
                pcc.id = i;


                binding.cl1.addView(pcc)
                val constraintSet = ConstraintSet()
                constraintSet.clone(binding.cl1)
                constraintSet.connect(pcc.id, ConstraintSet.TOP, binding.cl1.id, ConstraintSet.TOP, 0);
                constraintSet.connect(pcc.id, ConstraintSet.BOTTOM, binding.cl1.id, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(pcc.id, ConstraintSet.LEFT, binding.cl1.id, ConstraintSet.LEFT, 0);
                constraintSet.connect(pcc.id, ConstraintSet.RIGHT, binding.cl1.id, ConstraintSet.RIGHT, 0);

                constraintSet.applyTo(binding.cl1);

                val directory = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString())
                if (!directory.exists()) {
                    directory.mkdirs()
                }

                val fileName = "file.txt"
                val file = File(directory, fileName)

                try {
                    val outputStream = FileOutputStream(file)
                    outputStream.write(pcc.radius.toString().toByteArray())
                    outputStream.close()
                } catch (e: IOException) {
                    Log.e("Exception", "File write failed: $e")
                }

                val text = file.readText()
                // binding.textView2.text = text

            }
        }




        return binding.root
    }
}