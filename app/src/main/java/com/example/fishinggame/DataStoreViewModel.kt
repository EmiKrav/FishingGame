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
        const val V4_USER_KEY4 = "name4"
        const val V5_USER_KEY5 = "name5"
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

    fun saveEzerai(name4: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeString(V4_USER_KEY4, name4)
        }
    }

    //Getting the name of saved user
    val getEzerai = appContext.readString(V4_USER_KEY4).asLiveData()

    fun savePaveiksliukas(name5: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt(V5_USER_KEY5, name5)
        }
    }

    //Getting the name of saved user
    val getPaveiksliukas = appContext.readInt(V5_USER_KEY5).asLiveData()

}
