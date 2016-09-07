package com.cobble.cobblecard

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class CardService extends HostApduService {

    val TAG: String = "CardService"

    override def processCommandApdu(bytes: Array[Byte], bundle: Bundle): Array[Byte] = {
        val str = "Received apdu: " + byteArrayToHexString(bytes)
        Log.i(TAG, str)
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        Array[Byte](0, 0)
    }

    override def onDeactivated(i: Int): Unit = {}

    def byteArrayToHexString(buf: Array[Byte]): String = buf.map("%02X" format _).mkString
}
