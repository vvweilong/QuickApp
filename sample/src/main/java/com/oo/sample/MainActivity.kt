package com.oo.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.oo.annotationlib.InitAnnotation
import com.oo.annotationlib.InitAnnotationProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val temp = Temp()
        InitAnnotationProcess.bind(temp)
        test(temp.value)
    }

    fun test(a:Int){
        Log.i("aaaa","$a")
    }


    open class Temp{
        @InitAnnotation(20)
        var value:Int=0
    }
}
