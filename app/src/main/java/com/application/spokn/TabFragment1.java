package com.application.spokn;


import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javi G on 2/8/2017.
 */

public class TabFragment1 extends Fragment {
    private RecyclerView recyclerView;
    private ChallengesAdapter adapter;
    private List<Challenge> challengeList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //View v = inflater.inflate(R.layout.tab_fragment_1, container, false);
        //initCollapsingToolbar(this.getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        challengeList = new ArrayList<>();
        adapter = new ChallengesAdapter(this.getActivity(), challengeList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.ic_mic_none_black_24dp).into((ImageView) view.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.mipmap.album1,
                R.mipmap.album1,
                R.mipmap.album1,
                R.mipmap.album1,
                R.mipmap.album1,
                R.mipmap.album1,
                R.mipmap.album1,
                R.mipmap.album1,
                R.mipmap.album1,
                R.mipmap.album1,
                R.mipmap.album1};

        Challenge a = new Challenge(getString(R.string.challenge1_name), getString(R.string.challenge1_short),getString(R.string.challenge1_long), covers[0]);
        challengeList.add(a);

        a = new Challenge(getString(R.string.challenge2_name), getString(R.string.challenge2_short),getString(R.string.challenge2_long), covers[1]);
        challengeList.add(a);

        a = new Challenge(getString(R.string.challenge3_name), getString(R.string.challenge3_short),getString(R.string.challenge3_long), covers[2]);
        challengeList.add(a);

        a = new Challenge(getString(R.string.challenge4_name), getString(R.string.challenge4_short),getString(R.string.challenge4_long), covers[3]);
        challengeList.add(a);

        a = new Challenge(getResources().getString(R.string.challenge4_name),"Tongue Twisters", "Twist yo toungue", covers[4]);
        challengeList.add(a);

        a = new Challenge("True Challenge", "Sample short description","Long",covers[5]);
        challengeList.add(a);

        a = new Challenge("True Challenge", "Sample short description","Long", covers[6]);
        challengeList.add(a);

        a = new Challenge("True Challenge", "Sample short description","Long",covers[7]);
        challengeList.add(a);

        a = new Challenge("True Challenge", "Sample short description","Long", covers[8]);
        challengeList.add(a);

        a = new Challenge("True Challenge", "Sample short description","Long",covers[9]);
        challengeList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
