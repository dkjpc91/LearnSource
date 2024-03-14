package com.mithilakshar.learnsource.models
import java.io.Serializable

data class bookmodel(val name:String="",val description:String="",val image:String="",val speak:String="",
    val bookpdflink:String=""):Serializable {
}