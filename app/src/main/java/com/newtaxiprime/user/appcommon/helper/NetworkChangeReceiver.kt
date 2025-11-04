package com.newtaxiprime.user.appcommon.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.newtaxiprime.user.appcommon.pushnotification.Config


class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        /*String status = NetworkUtil.getConnectivityStatusString(context);

        Toast.makeText(context, status, Toast.LENGTH_LONG).show();*/
        val pushNotification = Intent(Config.NETWORK_CHANGES)
        LocalBroadcastManager.getInstance(context).sendBroadcast(pushNotification)
    }
}
