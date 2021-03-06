package com.swl.catchmovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.swl.catchmovie.FirebaseDAO.CatchMovieDAO;
import com.swl.catchmovie.FirebaseDAO.RatingDAO;
import com.swl.catchmovie.fragment.ProfileFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SupportActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    RatingBar ratingBar;
    Button submitRating;
    Button backtoProfile;
    CatchMovieDAO ratingDAO;
    UserRatingData userRatingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        userRatingData = new UserRatingData();
        // Get user id
        String userID = getIntent().getStringExtra("USER_ID");
        userRatingData.setUserID(userID);

        setFAQ();
        setRatingBar();
        setSubmitRating();
        setBacktoProfile();

        ratingDAO = new RatingDAO();
    }

    private void setFAQ() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = FAQDataManager.getFAQData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
/*        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });*/

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }

    private void setRatingBar() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                userRatingData.setUserRating(v);
                Toast.makeText(getApplicationContext(),
                        "You rate " + v,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //TODO: save rating from user to DB?
    private void setSubmitRating() {
        submitRating = (Button) findViewById(R.id.submitrating);
        submitRating.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                Toast.makeText(getApplicationContext(),
                                                        "Thank you for rating our app!",
                                                        Toast.LENGTH_SHORT).show();

                                                startActivity(new Intent(SupportActivity.this, MainActivity.class));

                                                // Save to Firebase
                                                ratingDAO.save(userRatingData);
                                            }
                                        }

        );


    }

    private void setBacktoProfile() {
        backtoProfile = (Button) findViewById(R.id.backtoprofile);
        backtoProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SupportActivity.this, MainActivity.class));
            }
        });
    }
}

