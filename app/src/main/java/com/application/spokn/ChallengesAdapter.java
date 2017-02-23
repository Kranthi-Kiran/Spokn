package com.application.spokn;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by http://www.androidhive.info/2016/05/android-working-with-card-view-and-recycler-view/ on 2/8/2017.
 */

public class ChallengesAdapter extends RecyclerView.Adapter<ChallengesAdapter.MyViewHolder> {
    private Context mContext;
    private List<Challenge> challengeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.desc);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
           // overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public ChallengesAdapter(Context mContext, List<Challenge> challengeList) {
        this.mContext = mContext;
        this.challengeList = challengeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.challenge_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Challenge challenge = challengeList.get(position);
        holder.title.setText(challenge.GetName());
        holder.desc.setText(challenge.GetShortDescription());

        // loading album cover using Glide library
        Glide.with(mContext).load(challenge.GetImage()).fitCenter().into(holder.thumbnail);

       /* holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/

        holder.thumbnail.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view){
                showCardPage(holder.thumbnail,position);
            }
        }));
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    /* void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_challenge_card, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }*/

    /**
        Showing card page when thumbnail clicked
     */

    private void showCardPage(View view, int position){
        //inflate card page
        CardPageFragment nextFrag= CardPageFragment.newInstance(challengeList.get(position).GetName(),challengeList.get(position).GetLongDescription());
        getRequiredActivity(view).getFragmentManager().beginTransaction()
                .replace(R.id.cardpagecontent, nextFrag, null)
                .addToBackStack(null)
                .commit();
    }

    private Activity getRequiredActivity(View req_view) {
        Context context = req_view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_get_info:
                    Toast.makeText(mContext, "Info", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return challengeList .size();
    }

}
