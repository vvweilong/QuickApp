package com.oo.annotationlib

import android.util.Log

/**
* create by 朱晓龙 2019/12/25 4:43 PM
 * 注解 解析器
*/
class InitAnnotationProcess {
    companion object{
        fun bind(obj:Any){
            Log.i("annotation", "bind: ")
            val fields = obj.javaClass.fields
            for (field in fields) {
                Log.i("annotation","${field.type.canonicalName}")
                val annotation = field.getAnnotation(InitAnnotation::class.java)
                Log.i("annotation", "${annotation.javaClass.canonicalName}")
                if (annotation != null) {
                    field.setInt(obj,annotation.value)
                }
            }
        }

    }
}