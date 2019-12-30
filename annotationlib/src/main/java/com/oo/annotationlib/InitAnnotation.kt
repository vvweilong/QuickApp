package com.oo.annotationlib

/**
* create by 朱晓龙 2019/12/25 4:32 PM
 * 成员变量注解
 * 运行时注解
*/
@Target(AnnotationTarget.VALUE_PARAMETER,AnnotationTarget.FIELD,AnnotationTarget.CLASS)//成员变量
@Retention(AnnotationRetention.RUNTIME)//运行时注解
annotation class InitAnnotation(val value:Int) {

}