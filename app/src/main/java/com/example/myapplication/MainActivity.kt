package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.ContentProvider
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Message
import android.telephony.PhoneNumberUtils
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var sendButton:Button
    private lateinit var binding:ActivityMainBinding
    private lateinit var message: String
    private lateinit var number:String
    private var CHANNEL_ID = "2"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sendButton = binding.sendbutton

        sendButton.setOnClickListener {
            //message = binding.editTextPhoneNO.text.toString()
          //  number = binding.eidttextr.text.toString()
            sendnotificaition()
           // sendsms(number,message)
        }


    }
    fun sendnotificaition(){

        val builder=NotificationCompat.Builder(this,CHANNEL_ID)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,"1",NotificationManager.IMPORTANCE_DEFAULT)
            val manager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
            manager.createNotificationChannel(channel)
            builder.setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("Notification").setContentText("POJSJFJPJOQJMPQPOMPMCQPOSCOP")
        }else{
            builder.setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("NOTOFICATION").setContentText("SAOPJDJPASOPDSJOPJOSPAD").setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
        val notificationcompatmanager=NotificationManagerCompat.from(this)
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),1)
        }else{
            notificationcompatmanager.notify(1,builder.build())
        }
        notificationcompatmanager.notify(1,builder.build())

    }
    fun sendsms(UserNumber:String,Usermessage:String){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),1)
        }else {

            if (UserNumber.isNotEmpty() && Usermessage.isNotEmpty()) {
                Log.e("notepmty","notempllt")
                val smsManager: SmsManager? = getSystemService(SmsManager::class.java)
                smsManager?.sendTextMessage(UserNumber, null, Usermessage, null, null)
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==1&& grantResults.size>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            val smsManager: SmsManager = this.getSystemService(SmsManager::class.java)
            smsManager.sendTextMessage(number, null, message, null, null)
        }
    }
}