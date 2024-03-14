package com.mithilakshar.learnsource.models

import java.io.Serializable

class homedata(
    var category:String="",
    var type:Int=1, val booklist: ArrayList<bookmodel>? = null):Serializable {
}