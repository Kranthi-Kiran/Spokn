package com.application.spokn;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Javi G on 2/8/2017.
 */

public class TabFragment2 extends Fragment {
    FloatingActionButton fab ;
    WatsonSpeechTT newSpeech;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_fragment_2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        newSpeech=new WatsonSpeechTT();
        newSpeech.SetFab(fab);
        newSpeech.SetActivity(this.getActivity());
        newSpeech.Init();


        fab= (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newSpeech.BeginSTT();
            }
        });

    }
}

