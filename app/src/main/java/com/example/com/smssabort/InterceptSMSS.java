package com.example.com.smssabort;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Administrator on 2016/7/17.
 */
public class InterceptSMSS extends BroadcastReceiver {
    String TAG = "InterceptSMSS";
    @Override

    public void onReceive(Context context, Intent intent) {
        Object [] objs = (Object[]) intent.getExtras().get("pdus");

        for(Object obj:objs)
        {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            String body = smsMessage.getDisplayMessageBody();
            String sender = smsMessage.getDisplayOriginatingAddress();
            Log.i(TAG, "body: "+body);
            Log.i(TAG, "sender "+sender);
            if("+8617862867761".equals(sender.toString().trim()))
            {
                abortBroadcast();
                Log.i("MessageReceiver","垃圾短信，立刻终止！");

            }
        }
    }
}
