package com.example.fishinggame

object Data {
    fun getRods(): List<Shop> {
        var ShopList: List<Shop> = listOf(
        Shop("Starter Rod", 0F,false,R.drawable.rod),
        Shop("Next Rod", 10F,true,R.drawable.rod2),
        Shop("Super Rod", 30F,true,R.drawable.rod3),
        Shop("Amazing Catcher", 100F,true,R.drawable.rod4),
        Shop("Expensive Stick", 1000F,true,R.drawable.rod5)
    )
        return ShopList
    }
    fun getReels(): List<Shop> {
        var ShopList2: List<Shop> = listOf(
        Shop("Starter Rite", 0F,false,R.drawable.rite),
        Shop("Cyan Rite", 20F,true,R.drawable.rite2)
    )
        return ShopList2
    }
    fun getFloats(): List<Shop> {
        var ShopList3: List<Shop> = listOf(
        Shop("Starter Plude", 0F,false,R.drawable.plude2),
        Shop("Next Plude", 2F,true,R.drawable.plude1)
    )
        return ShopList3
    }
    fun getPlaces(): List<Shop> {
        var ShopList: List<Shop> = listOf(
        Shop("Beginer's Bridge", 0F,false, com.example.fishinggame.R.drawable.tiltas),
        Shop("Mountains Lake", 100F,true, com.example.fishinggame.R.drawable.lake1),
        Shop("Green Place", 10F,true, com.example.fishinggame.R.drawable.lake2)
    )
        return ShopList
    }

}