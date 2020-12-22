package com.faysal.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;
import java.io.InputStream;

public class Privacy_Policy extends AppCompatActivity {
    private TextView textview_privacy_policy;
    private String str;
    private com.google.android.gms.ads.AdView AdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);
        textview_privacy_policy = findViewById(R.id.textview_privacy_policy);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView.loadAd(adRequest);






        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.privacy_policy));

        try {
            InputStream is = getAssets().open("privarcypolicy.txt");

            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            str = new String(buffer);

            // Finally stick the string into the text view.
            // Replace with whatever you need to have the text into.

            Log.d("text", str);

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

        textview_privacy_policy.setText(Html.fromHtml(str));

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onPause() {
        if (AdView != null) {
            AdView.pause();
            AdView.setVisibility(View.VISIBLE);
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AdView != null) {
            AdView.setVisibility(View.VISIBLE);
            AdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (AdView != null) {
            AdView.destroy();
            AdView.setVisibility(View.GONE);
        }
        super.onDestroy();
    }

}