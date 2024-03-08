package com.mithilakshar.learnsource

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mithilakshar.learnsource.ViewModels.BookViewModel
import com.mithilakshar.learnsource.ViewModels.BookViewModelFactory
import com.mithilakshar.learnsource.databinding.ActivityBookdescriptionsBinding
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


            viewModel.downloadFile("https://icseindia.org/document/sample.pdf","book.pdf")
            viewModel.downloadLiveData.observe(activity){
                when(it){
                    is myResponses.Error -> TODO()
                    is myResponses.Loading -> TODO()

                    is myResponses.Success -> {

                        val intent= Intent(this,pdfscreen::class.java)
                        startActivity(intent)
                    }

                    }
                }
            }


        }



    }
