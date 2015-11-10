package com.neveral.rgbcircles;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Neveral on 09.11.15.
 */
public class MyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    }
}
