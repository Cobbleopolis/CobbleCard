package com.cobble.cobblecard

import android.app.{Notification, NotificationManager, Service}
import android.content.Context
import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Toast

class CardService extends HostApduService {

    val TAG: String = "CardService"

    override def onCreate(): Unit = {
        super.onCreate()

        Toast.makeText(getApplicationContext, "Service Start", Toast.LENGTH_LONG).show()
        Log.d(TAG, "Service Started")
    }

    override def processCommandApdu(bytes: Array[Byte], bundle: Bundle): Array[Byte] = {
        val str = "Received apdu: " + byteArrayToHexString(bytes)
        Log.i(TAG, str)
        Toast.makeText(getApplicationContext, str, Toast.LENGTH_LONG).show()
        Array[Byte](0, 0)
    }

    override def onDeactivated(i: Int): Unit = {

    }

    override def onDestroy(): Unit = {
        super.onDestroy()

        Toast.makeText(getApplicationContext, "Service Stopped", Toast.LENGTH_LONG).show()
        Log.d(TAG, "Service Stopped")
    }

    def byteArrayToHexString(buf: Array[Byte]): String = buf.map("%02X" format _).mkString
}
