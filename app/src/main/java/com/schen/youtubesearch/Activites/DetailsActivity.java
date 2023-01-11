package com.schen.youtubesearch.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.schen.youtubesearch.Model.Video;
import com.schen.youtubesearch.R;
import com.squareup.picasso.Picasso;

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
        VideoThumbnailID_details = findViewById(R.id.videoThumbnailID_details);

        VideoDurationID_details.setText(video.getDuration().toString());
        VideoDescriptionID_details.setText(video.getDescription().toString());
        VideoTitleID_details.setText(video.getTitle().toString());
        VideoChannelID_details.setText(video.getAuthor().toString());
        VideoViewsID_details.setText(video.getViews().toString());
        VideoUploadedAtID_details.setText(video.getUploaded_at().toString());
        Picasso.get()
                .load(video.getThumbnail())
                .resize(300,300)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(VideoThumbnailID_details);
    }
}