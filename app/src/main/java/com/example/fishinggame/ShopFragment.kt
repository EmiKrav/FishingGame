package com.example.fishinggame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.PopupWindow
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fishinggame.databinding.FragmentShopBinding


class ShopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShopBinding.inflate(inflater)

        val viewModel: DataStoreViewModel by viewModels()

        viewModel.getPinigai.observe(viewLifecycleOwner) {
            val p = String.format("%.2f", it)
            binding.textView7.text = "Money: $p €";
        }

        val spinner = binding.spinner

        fun Spinner.avoidDropdownFocus() {
            try {
                val isAppCompat = this is androidx.appcompat.widget.AppCompatSpinner
                val spinnerClass = if (isAppCompat) androidx.appcompat.widget.AppCompatSpinner::class.java else Spinner::class.java
                val popupWindowClass = if (isAppCompat) androidx.appcompat.widget.ListPopupWindow::class.java else android.widget.ListPopupWindow::class.java

                val listPopup = spinnerClass
                    .getDeclaredField("mPopup")
                    .apply { isAccessible = true }
                    .get(this)
                if (popupWindowClass.isInstance(listPopup)) {
                    val popup = popupWindowClass
                        .getDeclaredField("mPopup")
                        .apply { isAccessible = true }
                        .get(listPopup)
                    if (popup is PopupWindow) {
                        popup.isFocusable = false
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        spinner.avoidDropdownFocus()

        val recycler = binding.recyclerView
        recycler.layoutManager = LinearLayoutManager(context)

        var subjects: MutableList<String?> = ArrayList();
        subjects.add("Vietos")
        subjects.add("Meškerės")
        subjects.add("Ritės")
        subjects.add("Plūdės")

        var adapter: ArrayAdapter<String?>?
        adapter = ArrayAdapter<String?>(
            requireContext(),
            R.layout.spinner_item,
            subjects
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        var e : String = ""
        var ShopList: List<Shop> = listOf(
            Shop("Beginer's Bridge", 0F,false,R.drawable.tiltas),
            Shop("Mountains Lake", 100F,true,R.drawable.lake1),
            Shop("Green Place", 10F,true,R.drawable.lake2)
        )
        viewModel.getEzerai.observe(viewLifecycleOwner) {
            e = it;
        }
        for (i  in ShopList.indices) {
            if (e.contains(ShopList[i].Pavadinimas)) {
                ShopList[i].Nupirkti = false
            }
        }
        var ShopList2: List<Shop> = listOf(
            Shop("Starter Rod", 0F,false,R.drawable.rod),
            Shop("Next Rod", 10F,true,R.drawable.rod2),
            Shop("Super Rod", 30F,true,R.drawable.rod3),
            Shop("Amazing Catcher", 100F,true,R.drawable.rod4),
            Shop("Expensive Stick", 1000F,true,R.drawable.rod5)
        )
        viewModel.getMeskeres.observe(viewLifecycleOwner) {
            e = it;
        }
        for (i  in ShopList2.indices) {
            if (e.contains(ShopList2[i].Pavadinimas)) {
                ShopList2[i].Nupirkti = false
            }
        }
        var ShopList3: List<Shop> = listOf(
            Shop("Starter Rite", 0F,false,R.drawable.rite),
            Shop("Cyan Rite", 20F,true,R.drawable.rite2)
        )
        viewModel.getRites.observe(viewLifecycleOwner) {
            e = it;
        }
        for (i  in ShopList3.indices) {
            if (e.contains(ShopList3[i].Pavadinimas)) {
                ShopList3[i].Nupirkti = false
            }
        }
        var ShopList4: List<Shop> = listOf(
            Shop("Starter Plude", 0F,false,R.drawable.plude2),
            Shop("Next Plude", 2F,true,R.drawable.plude1)
        )
        viewModel.getPludes.observe(viewLifecycleOwner) {
            e = it;
        }
        for (i  in ShopList4.indices) {
            if (e.contains(ShopList4[i].Pavadinimas)) {
                ShopList4[i].Nupirkti = false
            }
        }
        recycler.adapter = ShopAdapter(viewLifecycleOwner,viewModel, ShopList)
        var title = "Vietos";
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val text: String = parent?.getItemAtPosition(position).toString()
                if (text == "Vietos"){
                    recycler.adapter = ShopAdapter(viewLifecycleOwner,viewModel, ShopList)

                }
                if (text == "Meškerės"){
                    recycler.adapter = ShopAdapter2(viewLifecycleOwner,viewModel, ShopList2)
                }
                if (text == "Ritės"){
                    recycler.adapter = ShopAdapter3(viewLifecycleOwner,viewModel, ShopList3)
                }
                if (text == "Plūdės"){
                    recycler.adapter = ShopAdapter4(viewLifecycleOwner,viewModel, ShopList4)
                }

                title = text;
          }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }







        return binding.root
    }

}