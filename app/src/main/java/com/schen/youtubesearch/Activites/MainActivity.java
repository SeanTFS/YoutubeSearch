package com.schen.youtubesearch.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.schen.youtubesearch.Data.VideoRecyclerViewAdapter;
import com.schen.youtubesearch.Model.Video;
import com.schen.youtubesearch.R;
import com.schen.youtubesearch.Util.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VideoRecyclerViewAdapter videoRecyclerViewAdapter;
    private List<Video>  videoList;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        videoList=new ArrayList<>();

        Prefs prefs = new Prefs(MainActivity.this);
        String search = prefs.getSearch();
        videoList=getVideos(search);
        videoRecyclerViewAdapter=new VideoRecyclerViewAdapter(this,videoList);
        recyclerView.setAdapter(videoRecyclerViewAdapter);
        videoRecyclerViewAdapter.notifyDataSetChanged();
    }

    public List<Video> getVideos(String searchTerm)
    {
        videoList.clear();
        String myUrl = "https://youtube-search-results.p.rapidapi.com/youtube-search/?q="+searchTerm;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(myUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONArray videoArray=response.getJSONArray("items");
                    Log.d("videoresult","vid"+videoArray.toString());
                    /*for (int i = 0; i < videoArray.length(); i++)
                    {
                        JSONObject videoObj=videoArray.getJSONObject(i);

                        //the thumbnail url is a property of best thumbnail
                        //therefore, we have to get the object bestThumbnail in order to get the url
                        JSONObject thumbnailObj = videoObj.getJSONObject("bestThumbnail");
                        //same with author
                        JSONObject authorObj = videoObj.getJSONObject("author");
                        //if (videoObj.getString("type").equals("video"))
                        //{
                            Video video = new Video();
                            video.setTitle(videoObj.getString("title"));
                            video.setDescription(videoObj.getString("description"));
                            video.setDuration(videoObj.getString("duration"));
                            video.setViews(videoObj.getString("views"));
                            video.setUploaded_at(videoObj.getString("uploaded_at"));

                            video.setThumbnail(thumbnailObj.getString("url"));
                            video.setAuthor(authorObj.getString("name"));

                            videoList.add(video);
                        //}
                    }
                    videoRecyclerViewAdapter.notifyDataSetChanged();*/
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                   Map<String, String> params=new HashMap<>();
                   params.put("X-RapidAPI-Host","youtube-search-results.p.rapidapi.com");
                   params.put("X-RapidAPI-Key","940376fbf4msh2d7a502008fb4d0p1e7454jsna99c1293a39b");
                   return params;
            }
        };
        requestQueue.add(jsonObjectRequest);
        return videoList;
    }
}