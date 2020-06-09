package com.example.suyog.fingerprintauthentication;

import android.Manifest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;



public class  FingerprintHandler extends FingerprintManager.AuthenticationCallback{
    private Context context;

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuthentication(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cenCancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED)
            return;
        fingerprintManager.authenticate(cryptoObject,cenCancellationSignal,0,this,null);
    }
    
    @Override
    public  void onAuthenticationFailed(){
        super.onAuthenticationFailed();
        TextView messageText = (TextView)((Activity)context).findViewById(R.id.text_message);
        messageText.setText("Fingerprint not recognized. Try again");

    }
    @Override
    public  void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result){
        super.onAuthenticationSucceeded(result);
        context.startActivity(new Intent(context,HomeActivity.class));
    }
}
