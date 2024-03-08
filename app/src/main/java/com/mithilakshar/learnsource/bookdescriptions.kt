package com.mithilakshar.learnsource

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mithilakshar.learnsource.ViewModels.BookViewModel
import com.mithilakshar.learnsource.ViewModels.BookViewModelFactory
import com.mithilakshar.learnsource.databinding.ActivityBookdescriptionsBinding
import com.mithilakshar.learnsource.databinding.LayoutProgressBinding
import com.mithilakshar.learnsource.repository.repo
import com.mithilakshar.learnsource.utils.myResponses

class bookdescriptions : AppCompatActivity() {

    private lateinit var binding: ActivityBookdescriptionsBinding
    val activity = this
    private val repo=repo(activity)
    private val viewModel by lazy {
        ViewModelProvider(activity,BookViewModelFactory(repo))[BookViewModel::class.java]
         }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBookdescriptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mReadBookBtn.setOnClickListener {


            viewModel.downloadFile("https://cdn.shopify.com/s/files/1/2081/8163/files/002-GINGER-THE-GIRAFFE-Free-Childrens-Book-By-Monkey-Pen.pdf?v=1589846892","book.pdf")
            val dialogBinding = LayoutProgressBinding.inflate(layoutInflater)
            val dialog=Dialog(activity).apply {
                setCancelable(false)
                setContentView(dialogBinding.root)
                this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                this.window!!.setLayout( ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
            }

            viewModel.downloadLiveData.observe(activity){
                when(it){
                    is myResponses.Error -> dialog.dismiss()
                    is myResponses.Loading -> {
                        dialogBinding.mProgress.text = "${it.progress}%"
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            dialogBinding.mProgressBar.setProgress(it.progress, true)
                        } else {
                            dialogBinding.mProgressBar.progress = it.progress

                        }
                        dialog.show()
                    }

                    is myResponses.Success -> {
                        dialog.dismiss()

                        val intent= Intent(this,pdfscreen::class.java)
                        intent.putExtra("book.pdf", it.data?.filePath)
                        startActivity(intent)
                    }

                    }
                }
            }


        }



    }
