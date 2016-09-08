package com.cobble.cobblecard


import android.content.{ComponentName, Context, Intent, ServiceConnection}
import android.os.{Bundle, IBinder}
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity extends AppCompatActivity {

    protected val cardServiceConnection: ServiceConnection = new ServiceConnection {

        val TAG: String = "CARD_SERVICE_CONNECTION"

        override def onServiceDisconnected(name: ComponentName): Unit = {
            Log.d(TAG, "Card Service Disconnected")
        }

        override def onServiceConnected(name: ComponentName, service: IBinder): Unit = {
            Log.d(TAG, "Card Service Connected")
        }
    }

    lazy val appContext: Context = getApplicationContext
    lazy val cardServiceIntent: Intent = new Intent(appContext, classOf[CardService])

    override def onCreate(savedInstanceState: Bundle): Unit = {
        super.onCreate(savedInstanceState)
        appContext.bindService(cardServiceIntent, cardServiceConnection, Context.BIND_AUTO_CREATE)
        setContentView(R.layout.activity_main)
    }

    override def onStop(): Unit = {
        super.onStop()
        appContext.unbindService(cardServiceConnection)
    }
}
