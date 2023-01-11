package com.schen.youtubesearch.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.schen.youtubesearch.Model.Video;
import com.schen.youtubesearch.R;

public class DetailsActivity extends AppCompatActivity {
    private Video video;
    private TextView VideoDurationID_details, VideoDescriptionID_details, VideoTitleID_details,
            VideoChannelID_details, VideoViewsID_details, VideoUploadedAtID_details;
    private ImageView VideoThumbnailID_details;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        video = (Video) getIntent().getSerializableExtra("video");
        VideoDurationID_details = findViewById(R.id.videoDurationID_details);
        VideoDescriptionID_details = findViewById(R.id.videoDescriptionID_details);
        VideoTitleID_details = findViewById(R.id.videoTitleID_details);
        VideoChannelID_details = findViewById(R.id.videoChannelID_details);
        VideoViewsID_details = findViewById(R.id.videoViewsID_details);
        VideoUploadedAtID_details = findViewById(R.id.videoUploadedAtID_details);

    }
}