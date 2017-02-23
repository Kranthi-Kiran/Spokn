package com.application.spokn;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Javi G on 2/14/2017.
 */

public class CardPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.challenge_frag,container,false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        TextView desc= (TextView)view.findViewById(R.id.challengeDescription);
        TextView name = (TextView)view.findViewById(R.id.challengeTitle);
        desc.setText(getArguments().getString("description"));
        name.setText(getArguments().getString("name"));

        Button b_doit= (Button) view.findViewById(R.id.btn_doit);
        b_doit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("Title",getArguments().getString("name"));
                startActivity(intent);

            }
        });

    }

    public static CardPageFragment newInstance(String name, String description){
        CardPageFragment newFrag = new CardPageFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("description", description);

        newFrag.setArguments(args);
        return newFrag;
    }
}
