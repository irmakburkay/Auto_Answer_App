package com.example.autoanswerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.util.Date;

public class MyCallReceiver extends BroadcastReceiver {

    public String incomingName;
    public String incomingNumber;
    public Date incomingDate;
    public AudioManager audioManager;
    public String contact;
    public Cursor cursor;
    public Date id;
    public Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        audioManager=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        //cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        Toast.makeText(context,intent.getStringExtra(TelephonyManager.EXTRA_STATE),Toast.LENGTH_SHORT).show();


        if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            incomingDate = new Date();
            incomingName = "";

//                try {
//                    while(cursor.moveToNext()){
//                        contact=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER));
//                        if(contact.contains(incomingNumber)){
//                            incomingName=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                            break;
//                        }
//                    }
//                }catch (Exception e){
//                    incomingName=e.toString();
//                }

            set();
        }
        else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)
                ||intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

    }

    public void set(){
        if(incomingNumber != null && incomingName != null && incomingDate != null ){
            //Toast.makeText(context,incomingNumber+"\n"+incomingDate+"\n"+incomingName,Toast.LENGTH_SHORT).show();
//            MainActivity.info.setText(MainActivity.info.getText()+"\n"+incomingNumber+"\n"+incomingDate+"\n"+incomingName);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(incomingNumber,null,"mesaj",null,null);
            //Toast.makeText(context,"mesaj gönderildi",Toast.LENGTH_LONG).show();

        }

    }

}
