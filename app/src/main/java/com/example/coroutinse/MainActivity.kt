package com.example.coroutinse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutinse.databinding.ActivityMainBinding
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var customScope: CoroutineScope
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val exception = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.i(TAG,throwable.message.toString())
        }

        customScope = CoroutineScope(Dispatchers.IO)

        customScope.launch(exception) {

            val job1 = async {

                fackApi1()
            }

            val job2 = async {
                fackApi2()
            }

            val job3 = async {
                fackApi3()
            }

            Log.i(TAG , job1.await())
            Log.i(TAG , "job2.await()")

            Log.i(TAG , job2.await())
            Log.i(TAG , job3.await())


        }
        Log.i(TAG , "job2.awahjkhjkhjkit()")

    }

    private suspend fun fackApi1(): String {
        delay(1000)
        return "xxxx1"
   }
    private suspend fun fackApi2(): String {
        delay(2000)
        5/0

        return "xxxx2"
    }
    private suspend fun fackApi3(): String {
        delay(1000)
        return "xxxx3"
    }


override fun onDestroy() {
    super.onDestroy()
    customScope.cancel()
}

companion object {
    const val TAG = "MainActivity"
}
}

