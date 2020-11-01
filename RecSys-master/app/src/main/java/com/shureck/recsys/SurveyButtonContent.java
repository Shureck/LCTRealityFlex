package com.shureck.recsys;

public class SurveyButtonContent {
    int surveyButtonText;
    int surveyButtonImage;

    public SurveyButtonContent(int surveyButtonText, int surveyButtonImage) {
        this.surveyButtonText = surveyButtonText;
        this.surveyButtonImage = surveyButtonImage;
    }

    public int getSurveyButtonText() {
        return surveyButtonText;
    }

    public int getSurveyButtonImage() {
        return surveyButtonImage;
    }
}
