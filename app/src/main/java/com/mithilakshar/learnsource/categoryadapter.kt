package com.mithilakshar.learnsource

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mithilakshar.learnsource.databinding.ItemCategoryBinding
import com.mithilakshar.learnsource.models.bookmodel
import com.mithilakshar.learnsource.models.homedata

class categoryadapter(var list:ArrayList<bookmodel>, var context: Context, var navController: NavController):RecyclerView.Adapter<categoryadapter.categoryviewholder>() {

    class categoryviewholder(var binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){
        fun itembind(homeModel: bookmodel, context:Context, navController: NavController){

            binding.apply{

                Glide.with(context).load(homeModel.image).thumbnail(0.5f).into(mBookImage)
                mBookTitle.text=homeModel.name
                mBookDesc.text=homeModel.description
                mAuthorName.text=homeModel.speak


                binding.root.setOnClickListener {

                    val intent= Intent(context,bookdescriptions::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel)
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, Pair.create(binding.mBookImage,"BookTrans"))
                    context.startActivity(intent,options.toBundle())
                }

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