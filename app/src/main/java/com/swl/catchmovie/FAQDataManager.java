package com.swl.catchmovie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQDataManager {


    public static HashMap<String, List<String>> getFAQData() {
        final HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> ansq1 = new ArrayList<String>();
        ansq1.add("Catch Movie is an application that provides a unified platform for users to " +
                "get updated movie information across cinemas in Singapore: Cathay, Golden Village " +
                "and Shaw. It is developed to eliminate the trouble of having to view the" +
                " movie schedules across different cinema websites. ");
        List<String> ansq2 = new ArrayList<String>();
        ansq2.add("You can find Now Showing movies by clicking Now Showing tab at the bottom " +
                "navigation bar. You may click on any movie posters/titles to see detailed " +
                "information for that movie, including show times across different cinemas.");
        List<String> ansq3 = new ArrayList<String>();
        ansq3.add("Catch Movie currently does not support ticket bookings. However, you are able to " +
                "click on any of the available show times and " +
                "the app will direct you to the official cinema booking page accordingly.");
        List<String> ansq4 = new ArrayList<String>();
        ansq4.add("Catch Movie enables user to get information on latest movies that are currently playing across different cinemas in Singapore." +
                " It compiles show times from different cinemas in one single view, " +
                "thus allowing users to quickly compare and decide the timings that best suit their needs." +
                " Additionally, it also provides information on Upcoming movies and " +
                "allows user to see ongoing promotions across different cinemas.");


        expandableListDetail.put("What is Catch Movie app?", ansq1);
        expandableListDetail.put("Where can I see schedules for Now Showing movies?", ansq2);
        expandableListDetail.put("Can I book movie tickets from Catch Movie app?", ansq3);
        expandableListDetail.put("What features are available in Catch Movie app?", ansq4);

        return expandableListDetail;
    }


}
