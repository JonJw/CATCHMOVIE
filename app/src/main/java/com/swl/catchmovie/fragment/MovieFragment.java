package com.swl.catchmovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import com.swl.catchmovie.Movie;
import com.swl.catchmovie.MovieDetailsActivity;
import com.swl.catchmovie.app.MyApplication;
import com.swl.catchmovie.R;

public class MovieFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private static final String TAG = "MovieFragment";
    //private static final String TAG = MovieFragment.class.getSimpleName();

    // url to fetch movie items
    private static final String URL = "https://jsonstorage.net/api/items/1a483cd5-09aa-470c-aee6-5e0e47b3418f";

    private RecyclerView recyclerView;
    private List<Movie> itemsList;
    private StoreAdapter mAdapter;
    private boolean setBySearch = false;
    private String setBySearchTitle = "";
    private int setBySearchPosition;

    public MovieFragment() {
        // Required empty public constructor
    }
    public MovieFragment(int position)
    {
        if(position != -1)
        {
            setBySearch = true;
            setBySearchPosition = position;
        }
    }
    public static MovieFragment newInstance(String param1, String param2) {
        MovieFragment fragment = new MovieFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movielist, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        TextView status = (TextView) view.findViewById(R.id.click);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(view);
            }
        });
        itemsList = new ArrayList<>();
        mAdapter = new StoreAdapter(getActivity(), itemsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        fetchStoreItems();

        return view;
    }


    /**
     * fetching shopping item by making http call
     */
    private void fetchStoreItems() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getActivity(), "Couldn't fetch the store items! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<Movie> items = new Gson().fromJson(response.toString(), new TypeToken<List<Movie>>() {
                        }.getType());

                        itemsList.clear();
                        itemsList.addAll(items);

                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }

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

    /**
     * RecyclerView adapter class to render items
     * This class can go into another separate class, but for simplicity
     */
    class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {
        private Context context;
        private List<Movie> movieList;


        public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView name;
            public ImageView thumbnail;



            public MyViewHolder(View view) {
                super(view);
                name = view.findViewById(R.id.title);
                thumbnail = view.findViewById(R.id.thumbnail);
            }

        }


        public StoreAdapter(Context context, List<Movie> movieList) {
            this.context = context;
            this.movieList = movieList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.store_item_row, parent, false);

            return new MyViewHolder(itemView);

        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            if(setBySearch)
            {
                final Movie movie = movieList.get(setBySearchPosition);
                holder.name.setText(movie.getTitle());
                Glide.with(context)
                        .load(movie.getImage())
                        .into(holder.thumbnail);
                Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("image_url", movie.getImage());
                intent.putExtra("movie_name", movie.getTitle());
                intent.putExtra("movie_advice", movie.getMovieAdvice());
                intent.putExtra("movie_duration", movie.getMovieDuration());
                intent.putExtra("movie_director", movie.getMovieDirector());
                intent.putExtra("movie_cast", movie.getMovieCast());
                intent.putExtra("movie_distributor", movie.getMovieDistributor());
                intent.putExtra("movie_language", movie.getMovieLanguage());
                intent.putExtra("movie_releasedate", movie.getMovieReleaseDate());
                intent.putExtra("movie_synopsis", movie.getMovieSynopsis());
                context.startActivity(intent);
                setBySearch = false;
            }
            else {
                final Movie movie = movieList.get(position);
                holder.name.setText(movie.getTitle());

                Glide.with(context)
                        .load(movie.getImage())
                        .into(holder.thumbnail);
                holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onClick: clicked on: " + movie.getTitle());

                        Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra("image_url", movie.getImage());
                        intent.putExtra("movie_name", movie.getTitle());
                        intent.putExtra("movie_advice", movie.getMovieAdvice());
                        intent.putExtra("movie_duration", movie.getMovieDuration());
                        intent.putExtra("movie_director", movie.getMovieDirector());
                        intent.putExtra("movie_cast", movie.getMovieCast());
                        intent.putExtra("movie_distributor", movie.getMovieDistributor());
                        intent.putExtra("movie_language", movie.getMovieLanguage());
                        intent.putExtra("movie_releasedate", movie.getMovieReleaseDate());
                        intent.putExtra("movie_synopsis", movie.getMovieSynopsis());
                        context.startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }
    }


    public void showPopUp(View v){
        PopupMenu popup = new PopupMenu(getActivity(), v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        Log.d(TAG, "Trying to retrieve popup: started.");
        popup.show();
        Log.d(TAG, "Retrieved Popup: started.");
    }

    public static String getURL() {
        return URL;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        TextView status = getView().findViewById(R.id.click);
        Fragment fragment;
        switch(menuItem.getItemId()){
            case R.id.nowshowing:
                status.setText("Now Showing");
                Toast.makeText(getActivity(), "NowShowing Movies", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.upshowing:
                status.setText("Upcoming");
                Toast.makeText(getActivity(), "UpComing Movies", Toast.LENGTH_SHORT).show();

                fragment = new UpcomingMovieFragment();
                Log.d(TAG, "Calling loadFragment");
                loadFragment(fragment);
                return true;
            default:
                return false;

        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Log.d(TAG, "Entering new Fragment");
        transaction.replace(R.id.frame_container, fragment);

        transaction.commit();
        Log.d(TAG, "Entered new Fragment");
    }
}
