package com.example.fishinggame

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.fishinggame.databinding.FragmentShopBigItemBinding
import org.apache.commons.lang3.ClassUtils.getPackageName


class ShopBigItemFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShopBigItemBinding.inflate(inflater)
        binding.imageView9.scaleType = ImageView.ScaleType.CENTER_INSIDE

        binding.imageView9.setImageDrawable(Data.getPicture())

        return binding.root
    }

}