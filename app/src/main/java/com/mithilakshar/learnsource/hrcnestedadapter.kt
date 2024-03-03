package com.mithilakshar.learnsource

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mithilakshar.learnsource.databinding.HomenesteditemBinding
import com.mithilakshar.learnsource.databinding.HrcboditemBinding
import com.mithilakshar.learnsource.databinding.Hrcbook1Binding

class hrcnestedadapter(var list: ArrayList<homedata>,var context: Context):RecyclerView.Adapter<hrcnestedadapter.nestedviewholder>() {


    class nestedviewholder(var binding: HomenesteditemBinding):RecyclerView.ViewHolder(binding.root){
        fun itembind(homeModel:homedata,context:Context){

            binding.apply{

                binding.root.setOnClickListener {

                    val intent= Intent(context,bookdescriptions::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): nestedviewholder {
        var binding= HomenesteditemBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return nestedviewholder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: nestedviewholder, position: Int) {
        val currentdata=list.get(position)
        return holder.itembind(currentdata,context)
    }
}