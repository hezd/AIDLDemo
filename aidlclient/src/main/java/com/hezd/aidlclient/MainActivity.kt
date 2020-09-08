package com.hezd.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hezd.aidlserver.Book
import com.hezd.aidlserver.IBookManager
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "aidlclient"
    }
    // 1.创建ServiceConnection对象，用来注册服务连接监听
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            try {
                val bookManager = IBookManager.Stub.asInterface(service)
                val bookList = bookManager.bookList
                Log.d(TAG,"query book list:$bookList")
                bookManager.addBook(Book("Android开发", 300.0))
                Log.d(TAG,"query book list:$bookList")
                val lastBookList = bookManager.bookList
                Log.d(TAG,"query book list:$lastBookList")
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 2.指定包名和服务类名，绑定服务
        val intent = Intent()
        intent.setClassName("com.hezd.aidlserver","com.hezd.aidlserver.service.BookManagerService")
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)

    }



    override fun onDestroy() {
        super.onDestroy()
        // 3.页面销毁时服务解除绑定
        unbindService(serviceConnection)
    }


}
