package com.example.ch11_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.ch11_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 퍼미션 요청.. 사후처리..
        val launcher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            if(it.all{permission -> permission.value == true}){
                noti()
            }
            else{
                Toast.makeText(this, "permission denied..", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button.setOnClickListener() {
            // 33번전 하위에는 퍼미션이 없다..
            if (Build.VERSION.SDK_INT >= 33) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        "android.permission.POST_NOTIFICATION"
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    noti()
                } else {
                    launcher.launch(arrayOf("android.permission.POST_NOTIFICATION"))
                }
            } else {
                noti()
            }
        }
    }
    fun noti(){
        val mannager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("one", "name", NotificationManager.IMPORTANCE_LOW)
            channel.description = "description"
            mannager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, "one")
        }else{
            builder = NotificationCompat.Builder(this)
        }

        builder.setSmallIcon(android.R.drawable.ic_notification_clear_all)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("Title")
        builder.setContentText("Text")

        val intent = Intent(this, MyReceiver::class.java)
        val penddingIntet = PendingIntent.getBroadcast(this, 10, intent, PendingIntent.FLAG_IMMUTABLE)
        builder.setContentIntent(penddingIntet)

        mannager.notify(11, builder.build())
    }
}