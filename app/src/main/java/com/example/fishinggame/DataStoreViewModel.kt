package com.example.fishinggame

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataStoreViewModel(val appContext: Application) : AndroidViewModel(appContext) {

    companion object{
        const val V1_USER_KEY = "name"
        const val V2_USER_KEY2 = "name2"
        const val V3_USER_KEY3 = "name3"
    }

    fun savePinigai(name: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeFloat(V1_USER_KEY, name)
        }
    }

    //Getting the name of saved user
    val getPinigai = appContext.readFloat(V1_USER_KEY).asLiveData()

    fun saveKiekis(name2: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt(V2_USER_KEY2, name2)
        }
    }

    //Getting the name of saved user
    val getKiekis = appContext.readInt(V2_USER_KEY2).asLiveData()

    fun saveLaikai(name3: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeString(V3_USER_KEY3, name3)
        }
    }

    //Getting the name of saved user
    val getLaikai = appContext.readString(V3_USER_KEY3).asLiveData()

}
