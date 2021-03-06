package com.example.recorder;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, rec;
    private MediaPlayer play;
    private MediaRecorder record;
    private String FILE; //File path

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FILE = Environment.getExternalStorageDirectory() + "/tempRecord.3gpp";


        btn1 = (Button)findViewById(R.id.btn1); //Button 1
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                play = MediaPlayer.create(MainActivity.this, R.raw.d);
                play.start();
                play.setOnCompletionListener(new OnCompletionListener() {//When sound ends
                    @Override
                    public void onCompletion(MediaPlayer play) {
                        play.release();//Releases system resources

                    }
                });
            }


        });
        btn2 = (Button)findViewById(R.id.btn2); //Button 2
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                play = MediaPlayer.create(MainActivity.this, R.raw.a);
                play.start();
                play.setOnCompletionListener(new OnCompletionListener() {//When sound ends

                    public void onCompletion(MediaPlayer play) {
                        play.release();//Releases system resources

                    }
                });
            }


        });
        btn3 = (Button)findViewById(R.id.btn3); //Button 3
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                play = MediaPlayer.create(MainActivity.this, R.raw.g);
                play.start();
                play.setOnCompletionListener(new OnCompletionListener() {//When sound ends
                    @Override
                    public void onCompletion(MediaPlayer play) {
                        play.release();//Releases system resources

                    }
                });
            }


        });

        rec = (Button)findViewById(R.id.rec);//rec button
        rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rec.getText().toString().equals("Record")) {
                    try {
                        startRecord();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    rec.setText("End");
                }
                else if (rec.getText().toString().equals("End")) { //Stop Recording
                    stopRecord();
                    rec.setText("Play");

                }
                else if (rec.getText().toString().equals("Play")) {//Playback
                    try {
                        startPlayback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    rec.setText("Stop");

                }
                else {//Stop Playback
                    stopPlayback();
                    rec.setText("Record");
                }

            }

        });
    }
    public void startRecord() throws Exception{//Throws exceptions
        if (record!=null) {
            record.release();
        }
        File fileOut = new File(FILE);
        if (fileOut!=null) {
            fileOut.delete();//Overwrites existing file before recording
        }
        record = new MediaRecorder();
        record.setAudioSource(MediaRecorder.AudioSource.MIC);
        record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        record.setOutputFile(FILE);//File path

        record.prepare();
        record.start();
    }
    public void stopRecord() {
        record.stop();
        record.release();
    }
    public void startPlayback() throws Exception{
        if(play!=null) {
            play.stop();
            play.release();
        }
        play = new MediaPlayer();
        play.setDataSource(FILE);
        play.prepare();
        play.start();
        play.setOnCompletionListener(new OnCompletionListener() {//When sound ends
            @Override
            public void onCompletion(MediaPlayer play) {
                play.release();//Releases system resources

            }
        });
    }
    public void stopPlayback() {

    }
}
