package com.example.multimedia

import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.multimedia.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    private lateinit var binding:ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    val uri = Uri.parse("android.resource://"+packageName+"/"+R.raw.android)
    val mediaController = MediaController(this)
    mediaController.setAnchorView(binding.video)
        binding.video.setVideoURI(uri)
        binding.video.requestFocus()
        binding.video.start()

        binding.video.setOnCompletionListener {
            finish()
        }
    }


}