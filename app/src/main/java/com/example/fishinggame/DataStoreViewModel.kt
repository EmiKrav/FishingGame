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
        const val V6_USER_KEY6 = "name6"
        const val V7_USER_KEY7 = "name7"
        const val V8_USER_KEY8 = "name8"
        const val V9_USER_KEY9 = "name9"
        const val V10_USER_KEY10 = "name10"
        const val V11_USER_KEY11 = "name11"
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

    fun saveMeskeres(name6: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeString(V6_USER_KEY6, name6)
        }
    }

    //Getting the name of saved user
    val getMeskeres = appContext.readString(V6_USER_KEY6).asLiveData()

    fun savePludes(name7: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeString(V7_USER_KEY7, name7)
        }
    }

    //Getting the name of saved user
    val getPludes = appContext.readString(V7_USER_KEY7).asLiveData()
    fun saveRites(name8: String) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeString(V8_USER_KEY8, name8)
        }
    }

    //Getting the name of saved user
    val getRites = appContext.readString(V8_USER_KEY8).asLiveData()

    fun saveMeskeresPaveiksliukas(name9: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt(V9_USER_KEY9, name9)
        }
    }

    //Getting the name of saved user
    val getMeskeresPaveiksliukas = appContext.readInt(V9_USER_KEY9).asLiveData()

    fun saveRitesPaveiksliukas(name10: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt(V10_USER_KEY10, name10)
        }
    }

    //Getting the name of saved user
    val getRitesPaveiksliukas = appContext.readInt(V10_USER_KEY10).asLiveData()
    fun savePludesPaveiksliukas(name11: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            appContext.writeInt(V11_USER_KEY11, name11)
        }
    }

    //Getting the name of saved user
    val getPludesPaveiksliukas = appContext.readInt(V11_USER_KEY11).asLiveData()

}
