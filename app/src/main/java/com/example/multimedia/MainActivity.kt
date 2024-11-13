package com.example.multimedia

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.multimedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

        showButton(true, false, false, false)

        val mediaPlayer = MediaPlayer()
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)

        mediaPlayer.setAudioAttributes(audioAttributes.build())

        val uri = Uri.parse("android.resource://"+packageName+"/"+R.raw.musikk)
        mediaPlayer.setDataSource(this, uri)

        mediaPlayer.setOnCompletionListener {
            showButton(true, false, false, false)
        }

        binding.btnPlay.setOnClickListener{
            mediaPlayer.prepare()
            mediaPlayer.start()
            showButton(false, true, false, true)
        }
        binding.btnPause.setOnClickListener{
            if(mediaPlayer.isPlaying) mediaPlayer.pause()
            showButton(false, false, true, false)
        }
        binding.btnResume.setOnClickListener{
            mediaPlayer.start()
            showButton(false,true,false,true)
        }
        binding.btnStop.setOnClickListener{
            if(mediaPlayer.isPlaying) mediaPlayer.stop()
            showButton(true, false, false, false)
        }

        binding.btnVideo.setOnClickListener{
            val intent = Intent(this@MainActivity, VideoActivity::class.java)
            startActivity(intent)
        }

        binding.btnVideoStreaming.setOnClickListener{
            val intent = Intent(this@MainActivity, VideoStreamingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
        }
        mediaPlayer.release()
    }
    private fun showButton(
        isShowPlayButton: Boolean,
        isShowPauseButton: Boolean,
        isShowResumeButton: Boolean,
        isShowStopButton: Boolean,
    ){
        binding.apply{
            btnPlay.isEnabled = isShowPlayButton
            btnPause.isEnabled = isShowPauseButton
            btnResume.isEnabled = isShowResumeButton
            btnStop.isEnabled = isShowStopButton
        }
    }
}