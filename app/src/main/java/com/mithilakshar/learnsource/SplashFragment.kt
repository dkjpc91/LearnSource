package com.mithilakshar.learnsource

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mithilakshar.learnsource.databinding.FragmentSplashBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private var charIndex: Int = 0
    private val textToType="Learn Source"
    private lateinit var substring:String
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
        //function to create Typing Effect.
        startTypingEffect()


    }

    private fun startTypingEffect() {
        //function to create Typing Effect.
        val handler=Handler(Looper.getMainLooper())
        val delay: Long = 350 // Adjust the delay between each character

        handler.postDelayed(object :Runnable{
            override fun run() {

                if (charIndex<=textToType.length){

                    binding.splashText.text=textToType.substring(0,charIndex)

                    charIndex++

                    handler.postDelayed(this,delay)

                }
                else{

                    handler.postDelayed(object :Runnable{
                        override fun run() {
                            findNavController().navigate(R.id.action_splashFragment_to_onboardFragmentOne)
                        }
                    },1000)


                }

            }
        },delay)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSplashBinding.inflate(layoutInflater,container,false)
        return binding.root



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SplashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}