package a4.folio.Page;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import a4.folio.R;


/**
 * Created by amir on 7/8/2018.
 */

public class VideoPlayerPage extends AppCompatActivity {
    private VideoView videoView;
    private MediaController mediaController;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player_page);

        videoView = (VideoView) findViewById(R.id.videoPlayerPage_videoView);
        linearLayout = (LinearLayout) findViewById(R.id.videoPlayerPage_layout);
        mediaController = new MediaController(VideoPlayerPage.this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(getIntent().getStringExtra("url")));
        ViewGroup.LayoutParams layoutParams1 = videoView.getLayoutParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        layoutParams1.height = displayMetrics.heightPixels;
        layoutParams1.width = displayMetrics.widthPixels;
        videoView.setLayoutParams(layoutParams1);
        videoView.start();
    }
}
