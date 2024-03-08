package com.mithilakshar.learnsource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowCompat
import com.mithilakshar.learnsource.databinding.ActivityPdfscreenBinding

class pdfscreen : AppCompatActivity() {

    private lateinit var binding: ActivityPdfscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPdfscreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.root.setOnClickListener {
            launcher.launch("application/pdf")
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri -> uri?.let { binding.pdfview.fromUri(it).load() }
    }
}