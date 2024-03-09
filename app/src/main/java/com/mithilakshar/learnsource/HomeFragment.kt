package com.mithilakshar.learnsource


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.fragment.findNavController
import com.mithilakshar.learnsource.ViewModels.MainViewModel
import com.mithilakshar.learnsource.ViewModels.MainViewModelFactory
import com.mithilakshar.learnsource.databinding.FragmentHomeBinding
import com.mithilakshar.learnsource.repository.MainRepo
import com.mithilakshar.learnsource.utils.myResponses
import com.mithilakshar.learnsource.utils.networkcheck
import com.mithilakshar.learnsource.utils.springscroll

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {



    private lateinit var binding:FragmentHomeBinding
    var list= arrayListOf<homedata>()
    private lateinit var viewModel:MainViewModel





    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkChecker = networkcheck(requireActivity())
        if (networkChecker.isConnected()){
            Toast.makeText(requireActivity(),"Connected",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireActivity(),"Not Connected",Toast.LENGTH_LONG).show()
        }


        var homerecylceradapter= homerecylceradapter(list,requireContext(),findNavController())
        val repo = MainRepo(requireActivity())
        viewModel=ViewModelProvider(requireActivity(),MainViewModelFactory(repo))[MainViewModel::class.java]



        viewModel.getHomeData()
        viewModel.homeLiveData.observe(requireActivity(), Observer {
            when(it){
                is myResponses.Error -> {}
                is myResponses.Loading -> {}
                is myResponses.Success -> {
                    list.clear()
                    val tempList = it.data
                    tempList?.forEach {
                        list.add(it)
                    }

                    homerecylceradapter.notifyDataSetChanged()

                }
            }

        })
        binding.homeRecycler.adapter=homerecylceradapter




        springscroll().attachToRecyclerView(binding.homeRecycler)
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        return  binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}