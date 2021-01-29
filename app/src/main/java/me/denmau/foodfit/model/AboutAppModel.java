package me.denmau.foodfit.model;

public class AboutAppModel {

    // Member Variables
    private final int aboutAppBanner;
    private final String aboutAppTitle;
    private final String aboutAppDesc;
    private final int aboutAppSliderImage;

    public AboutAppModel(int aboutAppBanner, String aboutAppTitle, String aboutAppDesc, int aboutAppSliderImage) {
        this.aboutAppBanner = aboutAppBanner;
        this.aboutAppTitle = aboutAppTitle;
        this.aboutAppDesc = aboutAppDesc;
        this.aboutAppSliderImage = aboutAppSliderImage;
    }

   // Getters

    public int getAboutAppBanner() {
        return aboutAppBanner;
    }

    public String getAboutAppTitle() {
        return aboutAppTitle;
    }

    public String getAboutAppDesc() {
        return aboutAppDesc;
    }

    public int getAboutAppSliderImage() {
        return aboutAppSliderImage;
    }
}
