package com.mithilakshar.learnsource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mithilakshar.learnsource.databinding.ActivityBookcategoryBinding
import com.mithilakshar.learnsource.utils.springscroll

class bookcategory : AppCompatActivity() {

    private lateinit var binding: ActivityBookcategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBookcategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var list= arrayListOf<homedata>()
        list.add(homedata("Name1",0))
        list.add(homedata("Name2",1))
        list.add(homedata("Name3",0))
        list.add(homedata("Name4",1))

        var categoryadapter=categoryadapter(list,this, NavController(this))
        binding.mRvCategory.adapter=categoryadapter
        springscroll().attachToRecyclerView(binding.mRvCategory)

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