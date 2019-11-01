package com.swl.catchmovie.FirebaseDAO;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.swl.catchmovie.UserRatingData;

import java.util.List;
import java.util.Optional;

public class RatingDAO implements CatchMovieDAO<UserRatingData> {

    private DatabaseReference mDatabase;

    @Override
    public Optional<UserRatingData> get(long id) {
        return null;
    }

    @Override
    public List<UserRatingData> getAll() {
        return null;
    }

    @Override
    public void save(UserRatingData userRatingData) {
        mDatabase = FirebaseDatabase.getInstance().getReference("user-rating");
        mDatabase.child(userRatingData.getUserID()).setValue(userRatingData.getUserRating());
    }

    @Override
    public void update(UserRatingData userRatingData, String[] params) {

    }

    @Override
    public void delete(UserRatingData userRatingData) {

    }
}
