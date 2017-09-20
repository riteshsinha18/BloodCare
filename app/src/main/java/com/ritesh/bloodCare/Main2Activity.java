package com.ritesh.bloodCare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Main2Activity extends AppCompatActivity {

    private Button Button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.loginvidd);

        videoView.setDrawingCacheEnabled(true);
        videoView.setVideoURI(uri);
        videoView.setZOrderOnTop(true);
        videoView.requestFocus();
        videoView.start();
        Button1=(Button)findViewById(R.id.button2);
        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this,Main3Activity.class));
            }
    });
    }
}
