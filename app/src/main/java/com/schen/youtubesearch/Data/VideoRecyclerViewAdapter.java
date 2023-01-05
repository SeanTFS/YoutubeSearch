package com.schen.youtubesearch.Data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.schen.youtubesearch.Model.Video;
import com.schen.youtubesearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoRecyclerViewAdapter extends RecyclerView.Adapter<VideoRecyclerViewAdapter.ViewHolder> {
    private final Context context;
    private final List<Video> videoList;
    //constructeur de la classe
    public VideoRecyclerViewAdapter(Context context, List<Video> video){
        this.context=context;
        videoList=video;
        Log.d("videoListAdapt","list adapt"+videoList);
    }

    @NonNull
    @Override
    public VideoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topresult,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoRecyclerViewAdapter.ViewHolder holder, int position)
    {
        Log.d("video","hi");
        Video video = videoList.get(position);
        holder.title.setText(video.getTitle());
        String thumbnailLink = video.getThumbnail();
        holder.channel.setText(video.getAuthor());
        holder.views.setText(video.getViews());
        holder.uploaded_at.setText(video.getUploaded_at());
        holder.duration.setText(video.getDuration());
        holder.description.setText(video.getDescription());
        Picasso.get()
                .load(thumbnailLink)
                .fit()
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView thumbnail;
        TextView channel;
        TextView views;
        TextView uploaded_at;
        TextView duration;
        TextView description;
        public ViewHolder(@NonNull View itemView, Context context)
        {
            super(itemView);
            context=context;
            title=itemView.findViewById(R.id.videoTitleID);
            thumbnail=itemView.findViewById((R.id.videoThumbnailID));
            channel=itemView.findViewById((R.id.videoChannelID));
            views=itemView.findViewById((R.id.videoViewsID));
            uploaded_at=itemView.findViewById((R.id.videoUploadedAtID));
            duration=itemView.findViewById((R.id.videoDurationID));
            description=itemView.findViewById((R.id.videoDescriptionID));
        }
    }
}
