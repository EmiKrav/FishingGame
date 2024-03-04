package com.example.fishinggame

import android.R.string
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.OnTouchListener
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.fishinggame.databinding.FragmentCathingBinding
import kotlin.properties.Delegates
import kotlin.random.Random


class CathingFragment : Fragment() {

    private lateinit var binding : FragmentCathingBinding
    var pritrauktas : Int = 0


    var sk: Float = 0F
    var kiekis: Int = 0
    var Laikas: String =""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentCathingBinding.inflate(inflater)


        binding.imageView2.isVisible = false
        binding.button2.isVisible = true
        binding.button2.isClickable = true

        if (savedInstanceState !=null){
            sk = savedInstanceState.getFloat("Pinigai");
            kiekis = savedInstanceState.getInt("Kiekis");
            Laikas = savedInstanceState.getString("Laikas").toString();
            binding.button2.visibility = savedInstanceState.getInt("Kirtimas");
            if (binding.button2.visibility == VISIBLE) {
                binding.button2.isClickable = true;
            }
            else{
                binding.button2.isClickable = false;
                val screenX = resources.displayMetrics.widthPixels;
                val screenY = resources.displayMetrics.heightPixels;
                val x = screenX /
                        ( savedInstanceState.getFloat("ScreenX") / savedInstanceState.getFloat("X"));
                val y = screenY /
                        ( savedInstanceState.getFloat("ScreenY") / savedInstanceState.getFloat("Y"));
                if(x < screenX/4 &&  x > 0){
                   binding.Meskere.x = x;
                }
                else {
                    binding.Meskere.x = (screenX/4).toFloat();
                }

                binding.Meskere.y = y;

                pritrauktas = savedInstanceState.getInt("Pritraukta");
                binding.imageView2.visibility = savedInstanceState.getInt("Rodykle");
                binding.imageView2.rotation = savedInstanceState.getFloat("RodykleSukimas")
                suka()
                setClicked()
            }


        }
        else{
            val args = FishFragmentArgs.fromBundle(requireArguments())
            sk = args.Pinigai;
            kiekis = args.Kiekis;
            Laikas = args.Laikas.toString();
            suka()
            setClicked()
        }

        return binding.root
    }


    var kimba = true;

    private fun setClicked() {
        suka()
        if (binding.button2.isVisible) {
            binding.button2.isClickable = true
            binding.imageView2.isVisible = false
            binding.imageView2.visibility = INVISIBLE
            binding.button2.setOnClickListener {
                suka()
                if (kimba) {
                    binding.button2.isClickable = false
                    binding.button2.isVisible = false
                    binding.imageView2.isVisible = true
                    binding.imageView2.visibility = VISIBLE
                    show()
                    // Toast.makeText(getActivity(),"Užkirsta",Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            suka()
            show()
        }

    }
    var xDown = 0f
    var yDown = 0f
    var xUP = 0f
    var yUP = 0f



    @SuppressLint("ClickableViewAccessibility")
    private fun show() {



        val screenWidth = resources.displayMetrics.widthPixels
        val screenHeight = resources.displayMetrics.heightPixels
        val atskaitaX = screenWidth / 4
        val atskaitaY = screenHeight / 2


        binding.Meskere.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                val xMove: Float
                val yMove: Float
                xMove = event.getX()
                yMove = event.getY()

                val distX: Float = xMove - xDown
                val distY: Float = yMove - yDown

                if(binding.Meskere.getX() + distX + atskaitaX < screenWidth &&  binding.Meskere.getX() + distX > 0){
                    binding.Meskere.setX(binding.Meskere.getX() + distX)

                }
                if(binding.Meskere.getY() + distY + atskaitaY < screenHeight &&  binding.Meskere.getY() + distY > - atskaitaY){
                    binding.Meskere.setY(binding.Meskere.getY() + distY)
                }


                if (binding.imageView2.rotation == 0F) {
                    if (isRight(screenWidth, screenHeight)) {
                       // val TAG = "MyActivity";
                        binding.imageView2.visibility = INVISIBLE
                        suka()
                       // Log.v(TAG,suka().toString())
                        //     Log.v(TAG,"Rigth")
                     //   Log.v(TAG, binding.imageView2.rotation.toString())
                    }
                }
                if (binding.imageView2.rotation == 180F) {
                    if (isLeft(screenWidth, screenHeight)) {
                        //   val TAG = "MyActivity";
                        binding.imageView2.visibility = INVISIBLE
                        suka()
                        // Log.v(TAG,"Left")
                    }
                }
                if (binding.imageView2.rotation == 270F) {
                    if (isUP(screenWidth, screenHeight)) {
                        //   val TAG = "MyActivity";

                        binding.imageView2.visibility = INVISIBLE
                        suka()
                        // Log.v(TAG,"UP")
                    }
                }
                if (binding.imageView2.rotation == 90F) {
                    if (isDown(screenWidth, screenHeight)) {
                        //   val TAG = "MyActivity";

                        binding.imageView2.visibility = INVISIBLE
                        suka()
                        //    Log.v(TAG,"Down")
                    }
                }
                //   val TAG = "MyActivity";

             //   Log.v(TAG,"SH: "+ screenHeight);
               // Log.v(TAG,"SW: "+ screenWidth);
              //  Log.v(TAG,"MY: "+ binding.Meskere.getY().toString());
              //  Log.v(TAG,"MX: "+ binding.Meskere.getX().toString());
              //  Log.v(TAG,"DY: "+  distY );
              //  Log.v(TAG,"DX: "+  distX );
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

    private fun change(){

        pritrauktas++

        if (pritrauktas == 1){
            val action =
                CathingFragmentDirections.actionCathingFragmentToFishFragment(sk,kiekis,Laikas)
            findNavController().navigate(action)
        }
        else{
            val list = listOf(90F, 180F, 0F, 270F)
            val randomIndex = Random.nextInt(list.size);

            binding.imageView2.rotation = list[randomIndex]
            binding.imageView2.visibility = VISIBLE
        }
    }
    private fun suka() {
        // implement a setOnLongClickListener to the
        // button that creates a Toast and
        // returns true when actually long-pressed

        if (binding.imageView2.visibility == INVISIBLE) {
            binding.Rite.setOnLongClickListener {
                //  Toast.makeText(activity, "Button Long Pressed", Toast.LENGTH_SHORT).show()
                if (binding.imageView2.visibility == INVISIBLE) {
                    change()
                }
                true
            }
        }
    }
    private fun isRight(sx: Int, sy: Int): Boolean {
        return (binding.Meskere.x + binding.Meskere.measuredWidth > sx/2)

    }
    private fun isLeft(sx: Int, sy: Int): Boolean {
        return (binding.Meskere.x + binding.Meskere.measuredWidth < sx/2)

    }
    private fun isUP(sx: Int, sy: Int): Boolean {
        return (binding.Meskere.y + (binding.Meskere.measuredHeight / 2) < sy/2)

    }
    private fun isDown(sx: Int, sy: Int): Boolean {
        return (binding.Meskere.y + (binding.Meskere.measuredHeight /2 ) > sy/2)

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


          outState.putInt("Kirtimas", binding.button2.visibility);
          outState.putInt("Rodykle", binding.imageView2.visibility);
          outState.putFloat("RodykleSukimas", binding.imageView2.rotation);
          outState.putFloat("X", binding.Meskere.x)
          outState.putFloat("Y", binding.Meskere.y)
          outState.putFloat("ScreenX", resources.displayMetrics.widthPixels.toFloat())
          outState.putFloat("ScreenY", resources.displayMetrics.heightPixels.toFloat())
          outState.putInt("Pritraukta", pritrauktas)
        outState.putFloat("Pinigai", sk)
        outState.putInt("Kiekis", kiekis);
        outState.putString("Laikas", Laikas)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

