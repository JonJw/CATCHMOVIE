package com.swl.catchmovie;

public class PromotionInfo {
    private String PromotionInfo_name;
    //    private  String PromotionInfo_url;
    private String Promotion_Description;
    private int Promotion_thumbnail;

    public PromotionInfo(String promotionInfo_name, String promotion_Description, int promotion_thumbnail) {
        PromotionInfo_name = promotionInfo_name;
        Promotion_Description = promotion_Description;
        Promotion_thumbnail = promotion_thumbnail;
    }

    public String getPromotion_Description() {
        return Promotion_Description;
    }

    public void setPromotion_Description(String promotion_Description) {
        Promotion_Description = promotion_Description;
    }

    public PromotionInfo() {
    }

    public int getPromotion_thumbnail() {
        return Promotion_thumbnail;
    }

    public void setPromotion_thumbnail(int promotion_thumbnail) {
        Promotion_thumbnail = promotion_thumbnail;
    }

    public String getPromotionInfo_name() {
        return PromotionInfo_name;
    }

    public void setPromotionInfo_name(String promotionInfo_name) {
        this.PromotionInfo_name = promotionInfo_name;
    }

//    public String getPromotionInfo_url() {
//        return PromotionInfo_url;
//    }
//
//    public void setPromotionInfo_url(String promotionInfo_url) {
//        this.PromotionInfo_url = promotionInfo_url;
//    }
}
