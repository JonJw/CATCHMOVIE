package com.swl.catchmovie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQData {

    public static HashMap<String, List<String>> getFAQData()
    {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> ansq1 = new ArrayList<String>();
        ansq1.add("sample ans 1");
        List<String> ansq2 = new ArrayList<String>();
        ansq2.add("sample ans 2");
        List<String> ansq3 = new ArrayList<String>();
        ansq3.add("sample ans 3");



        expandableListDetail.put("Qn 1", ansq1);
        expandableListDetail.put("Qn 2", ansq2);
        expandableListDetail.put("Qn 3", ansq3);

        return expandableListDetail;
    }


}
