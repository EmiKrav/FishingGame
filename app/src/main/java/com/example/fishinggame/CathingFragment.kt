package com.example.fishinggame

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.fishinggame.databinding.FragmentCathingBinding


class CathingFragment : Fragment() {

    private lateinit var binding : FragmentCathingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCathingBinding.inflate(inflater)

        binding.fragment = this
        binding.lifecycleOwner = viewLifecycleOwner

        binding.imageView2.isVisible = false



        return binding.root
    }


    var kimba = true;

    private fun setClicked() {
        binding.button2.setOnClickListener {
            if(kimba){
                show()
               // Toast.makeText(getActivity(),"Užkirsta",Toast.LENGTH_SHORT).show();
            }
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClicked()
       // attachViewDragListener()
    }
    var xDown = 0f
    var yDown = 0f
    var xUP = 0f
    var yUP = 0f
    @SuppressLint("ClickableViewAccessibility")
    private fun show() {

        binding.imageView2.isVisible = true
        binding.button2.isVisible = false
        binding.button2.isClickable = false

        binding.Meskere.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                val xMove: Float
                val yMove: Float
                xMove = event.getX()
                yMove = event.getY()

                val distX: Float = xMove - xDown
                val distY: Float = yMove - yDown

                binding.Meskere.setX(binding.Meskere.getX() + distX)
                binding.Meskere.setY(binding.Meskere.getY() + distY)
            }
            if (event.action == MotionEvent.ACTION_DOWN) {
                xDown = event.getX();
                yDown = event.getY();

            }
            if (event.action == MotionEvent.ACTION_UP) {
                xUP = event.getX();
                yUP = event.getY();
            }
            true

        });
    }
}
