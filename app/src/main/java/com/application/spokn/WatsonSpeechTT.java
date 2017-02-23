package com.application.spokn;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.watson.developer_cloud.android.library.audio.MicrophoneInputStream;
import com.ibm.watson.developer_cloud.android.library.audio.utils.ContentType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.RecognizeCallback;

/**
 * Created by Javi G on 2/2/2017.
 */
/*
  Watson Speech Service credentials
  "url": "https://stream.watsonplatform.net/speech-to-text/api",
  "username": "db86e06c-309b-4c32-8e38-c36a2470872b",
  "password": "Rr51p0bjbK1Y"
 */




class WatsonSpeechTT {

    private SpeechToText speechService;
    private MicrophoneInputStream capture;
    private boolean listening = false;
    private FloatingActionButton fab;
    private Activity activity;

    public WatsonSpeechTT(){

    }

    public void Init(){
        speechService = initSpeechToTextService();
    }

    public void SetFab(FloatingActionButton fab){ this.fab=fab; }

    public void SetActivity(Activity ac){
        activity=ac;
    }

    public void BeginSTT(){
        if(listening != true) {
            capture = new MicrophoneInputStream(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        speechService.recognizeUsingWebSocket(capture, getRecognizeOptions(), new WatsonSpeechTT.MicrophoneRecognizeDelegate());
                    } catch (Exception e) {
                        showError(e);
                    }
                }
            }).start();
            listening = true;
        } else {
            try {
                capture.close();
                listening = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private SpeechToText initSpeechToTextService() {
        SpeechToText service = new SpeechToText();
        String username = activity.getString(R.string.speech_text_username);
        String password = activity.getString(R.string.speech_text_password);
        service.setUsernameAndPassword(username, password);
        service.setEndPoint("https://stream.watsonplatform.net/speech-to-text/api");
        return service;
    }


    private void showMicText(final String text) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView t = (TextView)activity.findViewById(R.id.hellow);
                t.setText(text);
            }
        });
    }

    private void showError(final Exception e) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }


    private RecognizeOptions getRecognizeOptions() {
        return new RecognizeOptions.Builder()
                .continuous(true)
                .contentType(ContentType.OPUS.toString())
                .model("en-US_BroadbandModel")
                .interimResults(true)
                .timestamps(true)
                .inactivityTimeout(2000)
                .build();
    }

    private void enableMicButton() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //fab.setEnabled(true);
            }
        });
    }


    private class MicrophoneRecognizeDelegate implements RecognizeCallback {

        @Override
        public void onTranscription(SpeechResults speechResults) {
            System.out.println(speechResults);
            String text = speechResults.getResults().get(0).getAlternatives().get(0).getTranscript();
            String tText="";
            try{
                tText=speechResults.getResults().get(0).getAlternatives().get(0).getTimestamps().toString();
            }
            catch(NullPointerException e){
                tText="err";
            }
            System.out.println(text);
            System.out.println(tText);
            showMicText(text);
        }

        @Override
        public void onConnected() {

        }

        @Override
        public void onError(Exception e) {
            showError(e);
            enableMicButton();
        }

        @Override
        public void onDisconnected() {
            enableMicButton();
        }
    }
}


