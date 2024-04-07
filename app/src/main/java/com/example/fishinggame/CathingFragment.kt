package com.example.fishinggame

import android.R
import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fishinggame.databinding.FragmentCathingBinding
import kotlin.random.Random


class CathingFragment : Fragment() {

    var pritrauktas : Int = 0
    var rnds = (1..10).random()

    var proc = (50..100).random()



    var but2 : Int = 0
    var imv2 : Int = 1
    var imv2r : Float = 0F;
    var MX : Float = 0F;
    var MY : Float = 0F;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var binding = FragmentCathingBinding.inflate(inflater);



        binding.imageView2.visibility = INVISIBLE
        imv2 = binding.imageView2.visibility
        binding.button2.visibility = VISIBLE
        but2 = binding.button2.visibility;
        binding.button2.isClickable = true
       // val TAG = "MyActivity";



        if (savedInstanceState !=null){
            binding.button2.visibility = savedInstanceState.getInt("Kirtimas");
            but2 =  binding.button2.visibility;
            if (binding.button2.visibility == VISIBLE) {
                binding.button2.isClickable = true;
                suka(binding)
                setClicked(binding)
            }
            else{
                binding.button2.isClickable = false;
                val screenX = resources.displayMetrics.widthPixels
                val screenY = resources.displayMetrics.heightPixels
                val desine = 2 * screenX / 4
                val apacia = 2* screenY / 4
                val virsus = 2 * screenY / 4
                val kaire = 0
                var x = savedInstanceState.getFloat("X");
                var y =  savedInstanceState.getFloat("Y");

                if(x + desine < screenX &&  x > kaire) {
                    binding.Meskere.x = x;
                }
                else {
                        while (x + desine > screenX){
                           x -= 1;
                       }
                        while (x < kaire){
                            x += 1;
                        }

                    binding.Meskere.x = x;
                }
                if(y + apacia < screenY &&  y > - virsus){
                    binding.Meskere.y = y
                }
                else{
                        while (y + apacia > screenY){
                            y -= 1;
                        }
                        while (y < - virsus){
                            y += 1;
                        }
                    binding.Meskere.y = y;
                }
             //   Log.v("TAG","MesYR: "+ binding.Meskere.y.toString());
             //   Log.v("Tag","MesYR: "+ binding.Meskere.x.toString());

                MX  = binding.Meskere.x;
                MY = binding.Meskere.y;

                pritrauktas = savedInstanceState.getInt("Pritraukta");
                rnds = savedInstanceState.getInt("Reikia");
                proc = savedInstanceState.getInt("Procentai");
                binding.imageView2.visibility = savedInstanceState.getInt("Rodykle");
                imv2 = binding.imageView2.visibility;
                binding.imageView2.rotation = savedInstanceState.getFloat("RodykleSukimas")
                imv2r = binding.imageView2.rotation;
                suka(binding)
                setClicked(binding)
            }


        }
        else{
            binding.button2.visibility = VISIBLE
            but2 = binding.button2.visibility;
            binding.button2.isClickable = true
            suka(binding)
            setClicked(binding)
        }

        return binding.root
    }


    var kimba = true;

    private fun setClicked(binding: FragmentCathingBinding) {
        suka(binding)
        if (binding.button2.visibility == VISIBLE) {
            but2 = binding.button2.visibility
            binding.button2.isClickable = true
            binding.imageView2.isVisible = false
            binding.imageView2.visibility = INVISIBLE
            imv2 = binding.imageView2.visibility
            binding.button2.setOnClickListener {
                suka(binding)
                if (kimba) {
                    binding.button2.isClickable = false
                    binding.button2.visibility = INVISIBLE
                    but2 = binding.button2.visibility;
                    binding.imageView2.isVisible = true
                    binding.imageView2.visibility = VISIBLE
                    imv2 = binding.imageView2.visibility
                    val list = listOf(90F, 180F, 0F, 270F)
                    val randomIndex = Random.nextInt(list.size);

                    binding.imageView2.rotation = list[randomIndex]
                    imv2r = binding.imageView2.rotation;
                   // Toast.makeText(activity,rnds.toString(),Toast.LENGTH_SHORT).show();
                    show(binding)
                }
            }
        }
        else{
            suka(binding)
            show(binding)
        }

    }
    var xDown = 0f
    var yDown = 0f


    @SuppressLint("ClickableViewAccessibility")
    private fun show(binding: FragmentCathingBinding) {



        val screenWidth = resources.displayMetrics.widthPixels
        val screenHeight = resources.displayMetrics.heightPixels
        val desine = 1 * screenWidth / 4
        val apacia = 2* screenHeight / 4
        val virsus = 2 * screenHeight / 4
        val kaire = 0


        binding.Meskere.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                val xMove: Float = event.x
                val yMove: Float = event.y

                val distX: Float = xMove - xDown
                val distY: Float = yMove - yDown

                if (binding.Meskere.x + distX + desine < screenWidth && binding.Meskere.getX() + distX > kaire) {
                    binding.Meskere.x = binding.Meskere.x + distX
                    MX = binding.Meskere.x;
                }
                if (binding.Meskere.y + distY + apacia < screenHeight && binding.Meskere.getY() + distY > -virsus) {
                    binding.Meskere.y = binding.Meskere.y + distY
                    MY = binding.Meskere.y;
                }


                if (binding.imageView2.rotation == 0F) {
                    if (isRight(screenWidth, screenHeight, binding)) {
                        // val TAG = "MyActivity";
                        binding.imageView2.visibility = INVISIBLE
                        imv2 = binding.imageView2.visibility
                        suka(binding)
                        // Log.v(TAG,suka().toString())
                        //     Log.v(TAG,"Rigth")
                        //   Log.v(TAG, binding.imageView2.rotation.toString())
                    }
                }
                if (binding.imageView2.rotation == 180F) {
                    if (isLeft(screenWidth, screenHeight, binding)) {
                        //   val TAG = "MyActivity";
                        binding.imageView2.visibility = INVISIBLE
                        imv2 = binding.imageView2.visibility
                        suka(binding)
                        // Log.v(TAG,"Left")
                    }
                }
                if (binding.imageView2.rotation == 270F) {
                    if (isUP(screenWidth, screenHeight, binding)) {
                        //   val TAG = "MyActivity";

                        binding.imageView2.visibility = INVISIBLE
                        imv2 = binding.imageView2.visibility
                        suka(binding)
                        // Log.v(TAG,"UP")
                    }
                }
                if (binding.imageView2.rotation == 90F) {
                    if (isDown(screenWidth, screenHeight, binding)) {
                        //   val TAG = "MyActivity";

                        binding.imageView2.visibility = INVISIBLE
                        imv2 = binding.imageView2.visibility
                        suka(binding)
                        //    Log.v(TAG,"Down")
                    }
                }
//                   val TAG = "MyActivity";
//
//                Log.v(TAG, "SH: $screenHeight");
//                Log.v(TAG, "SW: $screenWidth");

            }
            if (event.action == MotionEvent.ACTION_DOWN) {
                xDown = event.x;
                yDown = event.y;
            }
            true

        };

    }

    private fun change(binding: FragmentCathingBinding){

        pritrauktas++
        val list = listOf(90F, 180F, 0F, 270F)
        val randomIndex = Random.nextInt(list.size);

        binding.imageView2.rotation = list[randomIndex]
        imv2r = binding.imageView2.rotation;
       // Toast.makeText(activity, "$pritrauktas/$rnds", Toast.LENGTH_SHORT).show()
        if (proc  >= pritrauktas * 10) {
            if (pritrauktas == rnds) {
                val action =
                    CathingFragmentDirections.actionCathingFragmentToFishFragment()
                findNavController().navigate(action)
            } else {
                val list = listOf(90F, 180F, 0F, 270F)
                val randomIndex = Random.nextInt(list.size);

                binding.imageView2.rotation = list[randomIndex]
                imv2r = binding.imageView2.rotation;
                binding.imageView2.visibility = VISIBLE
                imv2 = binding.imageView2.visibility
            }
        }
        else {
            val action =
                CathingFragmentDirections.actionCathingFragmentToFailedFragment()
            findNavController().navigate(action)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun suka(binding: FragmentCathingBinding) {
        var duration : Long? = null
        var soundPool : SoundPool ?= SoundPool(5, AudioManager.STREAM_MUSIC, 0)
        var soundId = soundPool?.load(context, com.example.fishinggame.R.raw.ritesgarsas, 1)

        if (binding.imageView2.visibility == INVISIBLE && binding.button2.visibility == INVISIBLE && imv2 == INVISIBLE) {
            binding.Rite.setOnTouchListener() {v, event ->
             if (binding.imageView2.visibility == INVISIBLE) {
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        duration = System.currentTimeMillis()
                        if (soundPool != null) {
                            soundPool?.play(soundId!!, 1F, 1F, 0, 10, 1F)

                        }
                        else {
                           // Toast.makeText(activity, "jo", Toast.LENGTH_SHORT).show()
                            soundPool = SoundPool(5, AudioManager.STREAM_MUSIC, 0)
                            soundId = soundPool?.load(context, com.example.fishinggame.R.raw.ritesgarsas, 1)
                            soundPool?.play(soundId!!, 1F, 1F, 0, 10, 1F)

                        }


                    }
                    if (event.action == MotionEvent.ACTION_UP) {
                        if (duration != null && System.currentTimeMillis() - duration!! >= 500) {
                            binding.Rite.performLongClick()
                        }

                        soundPool?.pause(soundId!!)
                        soundPool?.release();
                        soundPool = null;
                    }
                }
                true
            }
            binding.Rite.setOnLongClickListener {

                //  Toast.makeText(activity, "Button Long Pressed", Toast.LENGTH_SHORT).show()
                if (binding.imageView2.visibility == INVISIBLE) {

                    change(binding)
                }

                true
            }


        }
    }
    private fun isRight(sx: Int, sy: Int, binding: FragmentCathingBinding): Boolean {
        return (binding.Meskere.x + binding.Meskere.measuredWidth > sx/2)

    }
    private fun isLeft(sx: Int, sy: Int, binding: FragmentCathingBinding): Boolean {
        return (binding.Meskere.x + binding.Meskere.measuredWidth < sx/2)

    }
    private fun isUP(sx: Int, sy: Int, binding: FragmentCathingBinding): Boolean {
        return (binding.Meskere.y + (binding.Meskere.measuredHeight / 2) < sy/2)

    }
    private fun isDown(sx: Int, sy: Int, binding: FragmentCathingBinding): Boolean {
        return (binding.Meskere.y + (binding.Meskere.measuredHeight /2 ) > sy/2)

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    outState.putInt("Kirtimas", but2);
    outState.putInt("Rodykle", imv2);
    outState.putFloat("RodykleSukimas", imv2r);
     outState.putFloat("X", MX)
     outState.putFloat("Y", MY)
    outState.putInt("Pritraukta", pritrauktas)
        outState.putInt("Reikia", rnds)
        outState.putInt("Procentai", proc)
    }

}

