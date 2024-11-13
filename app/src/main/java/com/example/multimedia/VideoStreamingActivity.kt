package com.example.multimedia

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.multimedia.databinding.ActivityVideoBinding
import com.example.multimedia.databinding.ActivityVideoStreamingBinding

class VideoStreamingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoStreamingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVideoStreamingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uri = Uri.parse("https://www.dropbox.com/s/2xziljidxo1jzva/Moana.mp4?dl=1")
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoStreaming)
        binding.videoStreaming.setVideoURI(uri)
        binding.videoStreaming.requestFocus()
        binding.videoStreaming.start()

        binding.videoStreaming.setOnCompletionListener {
            finish()
        }
    }
}