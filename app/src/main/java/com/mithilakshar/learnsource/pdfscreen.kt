package com.mithilakshar.learnsource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.view.WindowCompat
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.mithilakshar.learnsource.databinding.ActivityPdfscreenBinding

class pdfscreen : AppCompatActivity() {

    lateinit var bookPdf: String
    lateinit var bookId: String
    val activity = this

    private lateinit var binding: ActivityPdfscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPdfscreenBinding.inflate(layoutInflater)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupBasicViews()

        binding.apply {
            bookPdf = intent.getStringExtra("book_pdf").toString()
            bookId = intent.getStringExtra("book_id").toString()

            pdfview.fromUri(bookPdf.toUri())
                .swipeHorizontal(false)
                .scrollHandle(DefaultScrollHandle(activity))
                .enableSwipe(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .load()
        }



    }

    private fun setupBasicViews() {
        binding.mToolsFab.setOnClickListener {
            //val toolsBottomSheet = PdfToolsFragment()
            //toolsBottomSheet.show(supportFragmentManager, toolsBottomSheet.tag)
        }
    }
}