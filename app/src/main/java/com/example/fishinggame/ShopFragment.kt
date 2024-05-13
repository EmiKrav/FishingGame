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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.example.fishinggame.databinding.FragmentShopBinding
import javax.annotation.Nullable


class ShopFragment : Fragment() {

    var recycler : RecyclerView? = null
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



        var subjects: MutableList<String?> = ArrayList();
        subjects.add("Places")
        subjects.add("Rods")
        subjects.add("Reels")
        subjects.add("Floats")

        var adapter: ArrayAdapter<String?>?
        adapter = ArrayAdapter<String?>(
            requireContext(),
            R.layout.spinner_item,
            subjects
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        var e : String = ""
        var ShopList = Data.getPlaces()
        viewModel.getEzerai.observe(viewLifecycleOwner) {
            e = it;
        }
        for (i  in ShopList.indices) {
            if (e.contains(ShopList[i].Pavadinimas)) {
                ShopList[i].Nupirkti = false
            }
        }
        var ShopList2 = Data.getRods()
        viewModel.getMeskeres.observe(viewLifecycleOwner) {
            e = it;
        }
        for (i  in ShopList2.indices) {
            if (e.contains(ShopList2[i].Pavadinimas)) {
                ShopList2[i].Nupirkti = false
            }
        }
        var ShopList3 = Data.getReels()
        viewModel.getRites.observe(viewLifecycleOwner) {
            e = it;
        }
        for (i  in ShopList3.indices) {
            if (e.contains(ShopList3[i].Pavadinimas)) {
                ShopList3[i].Nupirkti = false
            }
        }
        var ShopList4 = Data.getFloats()
        viewModel.getPludes.observe(viewLifecycleOwner) {
            e = it;
        }
        for (i  in ShopList4.indices) {
            if (e.contains(ShopList4[i].Pavadinimas)) {
                ShopList4[i].Nupirkti = false
            }
        }
        if (savedInstanceState !=null) {
            recycler = binding.recyclerView
            recycler?.layoutManager = LinearLayoutManager(context)
            val scrollPosition = savedInstanceState.getInt("scrolled_position")
            recycler?.smoothScrollToPosition(scrollPosition)
        }
        else {

            recycler = binding.recyclerView
            recycler!!.layoutManager = LinearLayoutManager(context)
            //recycler!!.adapter?.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
            recycler!!.adapter = ShopAdapter(viewLifecycleOwner, viewModel, ShopList)
        }

        var title = "Places";
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {


                val text: String = parent?.getItemAtPosition(position).toString()
                Data.getPosition()?.let { recycler?.smoothScrollToPosition(it) }
                if (text == "Places"){
                    recycler?.adapter = ShopAdapter(viewLifecycleOwner,viewModel, ShopList)
                }
                if (text == "Rods"){
                    recycler?.adapter = ShopAdapter2(viewLifecycleOwner, viewModel, ShopList2)
                }
                if (text == "Reels"){
                    recycler?.adapter = ShopAdapter3(viewLifecycleOwner,viewModel, ShopList3)
                }
                if (text == "Floats"){
                    recycler?.adapter = ShopAdapter4(viewLifecycleOwner,viewModel, ShopList4)
                }


                title = text;
          }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

        return binding.root
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val layoutManager = recycler?.layoutManager as LinearLayoutManager
        outState.putInt(
            "scrolled_position",
            layoutManager.findLastVisibleItemPosition()
        )
    }


}