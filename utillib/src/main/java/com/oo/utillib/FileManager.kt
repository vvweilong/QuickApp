package com.oo.utillib

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File

/**
* create by 朱晓龙 2019/12/1 12:15 AM
 * 本地文件 工具类
*/
object FileManager {
     val TAG=javaClass.simpleName
    fun logInfos(context:Context){
        val rootDirectory = Environment.getRootDirectory()
        val externalStorageState = Environment.getExternalStorageState()
        val dataDirectory = Environment.getDataDirectory()
        val downloadCacheDirectory = Environment.getDownloadCacheDirectory()
        context.cacheDir
        Log.i(TAG, "rootDirectory: $rootDirectory")
        Log.i(TAG, "externalStorageState: $externalStorageState")
        Log.i(TAG, "dataDirectory: $dataDirectory")
        Log.i(TAG, "downloadCacheDirectory: $downloadCacheDirectory")
        Log.i(TAG, "cacheDir: ${context.cacheDir}")
        Log.i(TAG, "externalCacheDir: ${context.externalCacheDir}")
        Log.i(TAG, "filesDir: ${context.filesDir}")
        Log.i(TAG, "externalMediaDirs: ${context.externalMediaDirs}")
//        Log.i(TAG, "logInfos: ${getFileUri(context,dataDirectory)}")
    }


    fun getFileUri(context: Context,path:String): Uri? {
        val file = File(path)
        return getFileUri(context,file)
    }

    fun getFileUri(context: Context,file: File):Uri?{
        val applicationInfo = context.packageManager.getApplicationInfo(
            context.packageName,
            PackageManager.GET_META_DATA
        )
        val authStr = applicationInfo.metaData.getString("fileProviderAuth")?:""
        if (file.exists()&&!TextUtils.isEmpty(authStr)) {
            return FileProvider.getUriForFile(context,"${authStr}.fileProvider",file)
        }
        return null
    }

}