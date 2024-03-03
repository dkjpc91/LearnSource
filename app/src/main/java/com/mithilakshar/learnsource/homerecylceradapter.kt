package com.mithilakshar.learnsource

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import com.mithilakshar.learnsource.databinding.HrcboditemBinding
import com.mithilakshar.learnsource.databinding.Hrcbook1Binding

class homerecylceradapter(var datalist: ArrayList<homedata>,var context: Context,var navController: NavController) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class itemViewHolder(var binding:HrcboditemBinding):RecyclerView.ViewHolder(binding.root){
        fun itembind(homeModel:homedata,context:Context,navController: NavController){

            binding.apply{


                mSeeAllBtn.setOnClickListener {


                    navController.navigate(R.id.action_homeFragment_to_bookcategory)



                }

                var list= arrayListOf<homedata>()
                list.add(homedata("Name1",1))
                list.add(homedata("Name2",1))
                list.add(homedata("Name3",0))
                list.add(homedata("Name4",1))
                list.add(homedata("Name4",1))
                list.add(homedata("Name4",1))
                list.add(homedata("Name4",1))
                list.add(homedata("Name4",1))
                var hrcnestedadapter=hrcnestedadapter(list,context)

                hrcbodnested.adapter=hrcnestedadapter



            }
        }

    }
    class specialListViewHolder(var binding:Hrcbook1Binding):RecyclerView.ViewHolder(binding.root){
        fun specialbind(homeModel:homedata,context:Context,navController: NavController){

            binding.apply{

                mReadBookBtn.setOnClickListener { val intent= Intent(context,bookdescriptions::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent) }

                binding.root.setOnClickListener {

                    val intent= Intent(context,bookdescriptions::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)


                }

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            1 -> {
                itemViewHolder(
                    HrcboditemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                specialListViewHolder(
                    Hrcbook1Binding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

        }
    }



    override fun getItemViewType(position:Int):Int{
        return datalist.get(position).type
    }



    override fun getItemCount(): Int {
        return datalist.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentdata=datalist.get(position)
        when(currentdata.type){

            1->{(holder as itemViewHolder).itembind(currentdata,context,navController)}
            else->{(holder as specialListViewHolder).specialbind(currentdata,context,navController)}

        }
}}