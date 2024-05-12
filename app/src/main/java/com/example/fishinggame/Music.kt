package com.example.fishinggame

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener


object Music {

    var mediaPlayer: MediaPlayer? = null
    var sk: Int = 0
    fun CurrentTR(): Int {
        return sk
    }

    fun CreateMusic(context: Context) {
        mediaPlayer?.stop()
        mediaPlayer = null
        mediaPlayer = MediaPlayer.create(context, R.raw.watermain);
        mediaPlayer?.isLooping = true
        sk = 1
    }
    fun CreateMusic2(context: Context) {
        mediaPlayer?.stop()
        mediaPlayer=null
        mediaPlayer = MediaPlayer.create(context, R.raw.river);
        mediaPlayer?.isLooping = true
        sk = 2
    }
    fun CreateMusic3(context: Context) {
        mediaPlayer?.stop()
        mediaPlayer=null
        mediaPlayer = MediaPlayer.create(context, R.raw.spashing);
        mediaPlayer?.isLooping = false
        sk = 3
    }
    fun CreateMusic4(context: Context) {
        mediaPlayer?.stop()
        mediaPlayer=null
        mediaPlayer = MediaPlayer.create(context, R.raw.fail);
        mediaPlayer?.isLooping = false
        sk = 4
    }
    fun playMusic() {
        if (mediaPlayer?.isPlaying == true){

        }
        else {
            mediaPlayer?.start();
        }
    }
    fun pauseMusic(){
        mediaPlayer?.pause()
    }
    fun stopMusic(){
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}