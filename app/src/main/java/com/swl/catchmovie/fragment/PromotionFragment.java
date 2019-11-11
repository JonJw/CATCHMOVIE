package com.swl.catchmovie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swl.catchmovie.MainActivity;
import com.swl.catchmovie.ParentRecyclerAdapter;
import com.swl.catchmovie.R;
import com.swl.catchmovie.helper.BottomNavigationBehavior;

import java.util.ArrayList;

public class PromotionFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<String> itemsArrayList = new ArrayList<>();
    private ActionBar toolbar;

    public PromotionFragment() {
        // Required empty public constructor
    }

    public static PromotionFragment newInstance(String param1, String param2) {
        PromotionFragment fragment = new PromotionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_promotion, container, false);
//        TextView txtOpen = (TextView) view.findViewById(R.id.promotion);
//        txtOpen.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(getActivity(), PromotionActivity.class);
//                //intent.putExtra("Testing","123");
//                startActivity(intent);
//            }
//        });
//        return view;
        //setContentView(R.layout.activity_promotion);
        //layoutManager = new LinearLayoutManager(PromotionFragment.this);
        View view = inflater.inflate(R.layout.fragment_promotion, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.ParentRV);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ParentRecyclerAdapter(itemsArrayList, PromotionFragment.this.getContext());
        recyclerView.setAdapter(adapter);
        setData();
        MainActivity mainActivity = new MainActivity();
        //toolbar = getSupportActionBar();
        //BottomNavigationView navigation = mainActivity.getNavigation();
        //navigation.setOnNavigationItemSelectedListener(mainActivity.getmOnNavigationItemSelectedListener());


        // attaching bottom sheet behaviour - hide / show on scroll
        //CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        //layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        //toolbar.setTitle("Promotion");
        //loadFragment(new PromotionFragment());

        return view;

    }
    //@Override
//    public void onClick (View v) {
//
//        Intent i = new Intent(getActivity(), PromotionActivity.class);
//        startActivity(i);
//        //((Activity) getActivity()).overridePendingTransition(0, 0);
//
//    }

    private void setData() {
        String[] items = {"Current", "Discounts", "Movie Perks", "Food and Beverages", "Member's Exclusives", "Partnerships"};
        for (int i = 0; i < items.length; i++) {
            itemsArrayList.add(items[i]);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}

