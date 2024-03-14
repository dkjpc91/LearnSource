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
import com.mithilakshar.learnsource.databinding.HomebookitemBinding
import com.mithilakshar.learnsource.databinding.HrcboditemBinding
import com.mithilakshar.learnsource.databinding.Hrcbook1Binding
import com.mithilakshar.learnsource.models.bookmodel
import com.mithilakshar.learnsource.models.homedata
import com.mithilakshar.learnsource.utils.springscroll

class homerecylceradapter(var datalist: ArrayList<homedata>, var context: Context, var navController: NavController) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class itemViewHolder(var binding:HrcboditemBinding):RecyclerView.ViewHolder(binding.root){
        fun itembind(homeModel: homedata, context:Context, navController: NavController){
            val sharedPool = RecyclerView.RecycledViewPool()
            binding.apply{

                mCategoryTitle.text=homeModel.category


                mSeeAllBtn.setOnClickListener {

                    binding.apply {
                        mCategoryTitle.text=homeModel.category
                    }


                    val intent= Intent(context,bookcategory::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist)
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, Pair.create(hrcbodnested,"bookTrans"))
                    context.startActivity(intent,options.toBundle())



                }


                var list= arrayListOf<bookmodel>()

                if(homeModel.booklist!=null){
                    list= homeModel.booklist
                }
                val hrcnestedadapter=hrcnestedadapter(list,context)
                hrcbodnested.adapter=hrcnestedadapter
                hrcbodnested.setRecycledViewPool(sharedPool)
                springscroll().attachToRecyclerView(hrcbodnested)



            }
        }

    }
    class specialListViewHolder(var binding:Hrcbook1Binding):RecyclerView.ViewHolder(binding.root){
        fun specialbind(homeModel: homedata, context:Context, navController: NavController){

            binding.apply{

                textView.text=homeModel.category
                Glide.with(context).load(homeModel.booklist?.get(0)?.image).thumbnail(0.5f).into(imageView)

                mReadBookBtn.setOnClickListener { val intent= Intent(context,bookdescriptions::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist?.get(0))
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, Pair.create(imageView,"BookTrans"))
                    context.startActivity(intent,options.toBundle()) }

                binding.root.setOnClickListener {

                    val intent= Intent(context,bookdescriptions::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist?.get(0))
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, Pair.create(imageView,"BookTrans"))
                    context.startActivity(intent,options.toBundle())




                }

            }


        }

    }
    class specialListViewHolder1(var binding:HomebookitemBinding):RecyclerView.ViewHolder(binding.root){
        fun specialbind(homeModel: homedata, context:Context, navController: NavController){

            binding.apply{


                Glide.with(context).load(homeModel.booklist?.get(0)?.image).thumbnail(0.5f).into(imageView)
                textview.text=homeModel.category

                imageView.setOnClickListener { val intent= Intent(context,bookdescriptions::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist?.get(0))
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, Pair.create(imageView,"BookTrans"))
                    context.startActivity(intent,options.toBundle()) }

                binding.root.setOnClickListener {

                    val intent= Intent(context,bookdescriptions::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("bookdata",homeModel.booklist?.get(0))
                    val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity, Pair.create(imageView,"BookTrans"))
                    context.startActivity(intent,options.toBundle())




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

            2 -> {
                specialListViewHolder1(
                    HomebookitemBinding.inflate(
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
            2->{(holder as specialListViewHolder1).specialbind(currentdata,context,navController)}
            else->{(holder as specialListViewHolder).specialbind(currentdata,context,navController)}

        }
}}


