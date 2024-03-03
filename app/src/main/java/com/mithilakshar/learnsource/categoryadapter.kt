package com.mithilakshar.learnsource

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.learnsource.databinding.ItemCategoryBinding

class categoryadapter(var list:ArrayList<homedata>,var context: Context,var navController: NavController):RecyclerView.Adapter<categoryadapter.categoryviewholder>() {

    class categoryviewholder(var binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){
        fun itembind(homeModel:homedata,context:Context,navController: NavController){

            binding.apply{


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryviewholder {
        var binding= ItemCategoryBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return categoryviewholder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: categoryviewholder, position: Int) {

        val currentdata=list.get(position)
        return holder.itembind(currentdata,context,navController)

    }

}