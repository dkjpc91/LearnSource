package com.mithilakshar.learnsource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.mithilakshar.learnsource.ViewModels.MainViewModel
import com.mithilakshar.learnsource.ViewModels.MainViewModelFactory
import com.mithilakshar.learnsource.databinding.ActivityBookcategoryBinding
import com.mithilakshar.learnsource.repository.MainRepo
import com.mithilakshar.learnsource.utils.myResponses
import com.mithilakshar.learnsource.utils.springscroll

class bookcategory : AppCompatActivity() {

    private lateinit var binding: ActivityBookcategoryBinding

    var list= arrayListOf<homedata>()
    val activity = this
    var categoryadapter=categoryadapter(list,this, NavController(this))
    private val repo = MainRepo(this)
    private val viewModel by lazy {
        ViewModelProvider(this,MainViewModelFactory(repo))[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBookcategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getHomeData()
        viewModel.homeLiveData.observe(activity, Observer {
            when(it){
                is myResponses.Error -> {}
                is myResponses.Loading -> {}
                is myResponses.Success -> {
                    list.clear()
                    val tempList = it.data
                    tempList?.forEach {
                        list.add(it)
                    }

                    categoryadapter.notifyDataSetChanged()

                }
            }

        })





        binding.mRvCategory.adapter=categoryadapter
        springscroll().attachToRecyclerView(binding.mRvCategory)

    }

    private fun handleHomeBackend() {

        }


    override fun onBackPressed() {
        finish()
        window.sharedElementEnterTransition = null
        window.sharedElementReenterTransition=null
        window.sharedElementReturnTransition=null
        window.sharedElementExitTransition=null

        super.onBackPressed()
    }
}