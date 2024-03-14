package com.mithilakshar.learnsource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.mithilakshar.learnsource.ViewModels.MainViewModel
import com.mithilakshar.learnsource.ViewModels.MainViewModelFactory
import com.mithilakshar.learnsource.databinding.ActivityBookcategoryBinding
import com.mithilakshar.learnsource.models.bookmodel
import com.mithilakshar.learnsource.models.homedata
import com.mithilakshar.learnsource.repository.MainRepo
import com.mithilakshar.learnsource.utils.myResponses
import com.mithilakshar.learnsource.utils.springscroll

class bookcategory : AppCompatActivity() {

    private lateinit var binding: ActivityBookcategoryBinding

    val activity = this

    private val repo = MainRepo(this)
    private val viewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(repo))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookcategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var list = arrayListOf<bookmodel>()

        var categoryadapter = categoryadapter(list, this, NavController(this))
        binding.mRvCategory.adapter = categoryadapter

        val homedata = intent.getSerializableExtra("bookdata") as ArrayList<bookmodel>
        homedata.forEach {
            list.add(it)

            categoryadapter.notifyDataSetChanged()






            springscroll().attachToRecyclerView(binding.mRvCategory)

        }


    }

    override fun onBackPressed() {
        finish()
        window.sharedElementEnterTransition = null
        window.sharedElementReenterTransition = null
        window.sharedElementReturnTransition = null
        window.sharedElementExitTransition = null

        super.onBackPressed()
    }

}

