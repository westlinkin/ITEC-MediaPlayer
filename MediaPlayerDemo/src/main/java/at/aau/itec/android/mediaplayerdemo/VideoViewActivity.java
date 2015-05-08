/*
 * Copyright (c) 2014 Mario Guggenberger <mg@itec.aau.at>
 *
 * This file is part of ITEC MediaPlayer.
 *
 * ITEC MediaPlayer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ITEC MediaPlayer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ITEC MediaPlayer.  If not, see <http://www.gnu.org/licenses/>.
 */

package at.aau.itec.android.mediaplayerdemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.MediaController;
import android.widget.Toast;

import at.aau.itec.android.mediaplayer.MediaPlayer;
import at.aau.itec.android.mediaplayer.MediaSource;
import at.aau.itec.android.mediaplayer.VideoView;

public class VideoViewActivity extends Activity {

    private static final String TAG = VideoViewActivity.class.getSimpleName();

//    String video720_mp4 = "https://r5---sn-a5m7lne7.googlevideo.com/videoplayback?ipbits=0&initcwndbps=13492500&ratebypass=yes&sver=3&expire=1431079544&mime=video%2Fmp4&pl=21&itag=22&dur=301.395&source=youtube&requiressl=yes&fexp=907263%2C924637%2C934954%2C938028%2C9406690%2C9407664%2C9407807%2C9408142%2C9408704%2C9409095%2C9409253%2C9412773%2C9413043%2C9413159%2C9413342%2C948124%2C948802%2C952612%2C952637%2C952642&gcr=us&id=o-AOIWOPnos8vRkLFAUjyB2VTFinkY-O01fzighW6DEToa&sparams=dur%2Cgcr%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Cmime%2Cmm%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cusequic%2Cexpire&mm=31&upn=S7YPoVVXZFE&key=yt5&ip=198.136.25.82&ms=au&mt=1431057866&mv=m&usequic=yes&signature=E4FA1319444B60D0AC83FBED8F87FD607FB6969A.E277F1F6DF59C7207D6BE05A77CF0ACC715A10DF";
    String audio = "https://r5---sn-a5m7lne7.googlevideo.com/videoplayback?mm=31&id=o-AM2IJBt2FXMkr6APt3WD6TrFEVu3snMqHWJWVtI_CMmH&keepalive=yes&ip=198.136.25.82&pl=21&mv=m&mt=1431067480&ms=au&ipbits=0&initcwndbps=16512500&sparams=clen%2Cdur%2Cgcr%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Ckeepalive%2Clmt%2Cmime%2Cmm%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cupn%2Cusequic%2Cexpire&mime=audio%2Fmp4&fexp=907263%2C916729%2C934954%2C936109%2C938028%2C9406849%2C9407441%2C9407469%2C9408142%2C9408706%2C9409252%2C9409294%2C9412881%2C945137%2C948124%2C952612%2C952637%2C952642&key=yt5&gir=yes&clen=4839272&requiressl=yes&gcr=us&dur=301.394&source=youtube&lmt=1422017574025758&usequic=yes&expire=1431089124&upn=R_aEnzXaGqw&sver=3&itag=140&signature=02DAB7C015EBBD51C336B3FAB7DF52B89C422AD3.495EC6862DBD9A5A42D2997D26F33F77CEB07373";
    String video = "https://r5---sn-a5m7lne7.googlevideo.com/videoplayback?mm=31&id=o-AM2IJBt2FXMkr6APt3WD6TrFEVu3snMqHWJWVtI_CMmH&keepalive=yes&ip=198.136.25.82&pl=21&mv=m&mt=1431067480&ms=au&ipbits=0&initcwndbps=16512500&sparams=clen%2Cdur%2Cgcr%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Ckeepalive%2Clmt%2Cmime%2Cmm%2Cms%2Cmv%2Cpl%2Crequiressl%2Csource%2Cupn%2Cusequic%2Cexpire&mime=video%2Fwebm&fexp=907263%2C916729%2C934954%2C936109%2C938028%2C9406849%2C9407441%2C9407469%2C9408142%2C9408706%2C9409252%2C9409294%2C9412881%2C945137%2C948124%2C952612%2C952637%2C952642&key=yt5&gir=yes&clen=106875640&requiressl=yes&gcr=us&dur=301.301&source=youtube&lmt=1421306072038871&usequic=yes&expire=1431089124&upn=R_aEnzXaGqw&sver=3&itag=248&signature=E7159E42E7AA8AAF75F35ADC2A4DD8911BD9136F.2B95E57372EB5F1AD5DE0E601A3DAC37489755A1";

    private Uri mVideoUri = Uri.parse(video);
    private Uri mAudioUri = Uri.parse(audio);
    private VideoView mVideoView;

    private MediaController.MediaPlayerControl mMediaPlayerControl;
    private MediaController mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        Utils.setActionBarSubtitleEllipsizeMiddle(this);

        mVideoView = (VideoView) findViewById(R.id.vv);

        mMediaPlayerControl = mVideoView; //new MediaPlayerDummyControl();
        mMediaController = new MediaController(this);
        mMediaController.setAnchorView(findViewById(R.id.container));
        mMediaController.setMediaPlayer(mMediaPlayerControl);

//        if(savedInstanceState != null) {
//            initPlayer((Uri)savedInstanceState.getParcelable("uri"),
//                    savedInstanceState.getInt("position"),
//                    savedInstanceState.getBoolean("playing"));
//        } else {
            initPlayer(-1, false);
//        }
    }

    private void initPlayer(final int position, final boolean playback) {
//        mVideoUri = uri;
        getActionBar().setSubtitle(mVideoUri + "");

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer vp) {
                if (position > 0) {
                    mVideoView.seekTo(position);
                }
                if (playback) {
                    mVideoView.start();
                }
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                String whatName = "";
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        whatName = "MEDIA_INFO_BUFFERING_END";
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        whatName = "MEDIA_INFO_BUFFERING_START";
                        break;
                    case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        whatName = "MEDIA_INFO_VIDEO_RENDERING_START";
                        break;
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                        whatName = "MEDIA_INFO_VIDEO_TRACK_LAGGING";
                        break;
                }
                Log.d(TAG, "onInfo " + whatName);
                return false;
            }
        });
        mVideoView.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {
                Log.d(TAG, "onSeekComplete");
            }
        });
        mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
//                Log.d(TAG, "onBufferingUpdate " + percent + "%");
            }
        });
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });

        Utils.uriToMediaSourceAsync(this, mVideoUri, mAudioUri, new Utils.MediaSourceAsyncCallbackHandler() {
            @Override
            public void onMediaSourceLoaded(MediaSource mediaSource) {
                mVideoView.setVideoSource(mediaSource);
            }

            @Override
            public void onException(Exception e) {
                Log.e(TAG, "error loading video", e);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.videoview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_slowspeed) {
            mVideoView.setPlaybackSpeed(0.2f);
            return true;
        } else if(id == R.id.action_halfspeed) {
            mVideoView.setPlaybackSpeed(0.5f);
            return true;
        } else if(id == R.id.action_doublespeed) {
            mVideoView.setPlaybackSpeed(2.0f);
            return true;
        } else if(id == R.id.action_normalspeed) {
            mVideoView.setPlaybackSpeed(1.0f);
            return true;
        } else if(id == R.id.action_seekcurrentposition) {
            mVideoView.pause();
            mVideoView.seekTo(mVideoView.getCurrentPosition());
            return true;
        } else if(id == R.id.action_seekcurrentpositionplus1ms) {
            mVideoView.pause();
            mVideoView.seekTo(mVideoView.getCurrentPosition()+1);
            return true;
        } else if(id == R.id.action_seektoend) {
            mVideoView.pause();
            mVideoView.seekTo(mVideoView.getDuration());
            return true;
        } else if(id == R.id.action_getcurrentposition) {
            Toast.makeText(this, "current position: " + mVideoView.getCurrentPosition(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mMediaController.isShowing()) {
                mMediaController.hide();
            } else {
                mMediaController.show();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStop() {
        mMediaController.hide();
        super.onStop();
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        if(mVideoUri != null) {
//            outState.putParcelable("uri", mVideoUri);
//            outState.putBoolean("playing", mVideoView.isPlaying());
//            outState.putInt("position", mVideoView.getCurrentPosition());
//        }
//    }
}
