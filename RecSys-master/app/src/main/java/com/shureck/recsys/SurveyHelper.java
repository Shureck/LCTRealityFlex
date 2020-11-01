package com.shureck.recsys;

import java.util.ArrayList;

public class SurveyHelper {

    static ArrayList<SurveyButtonContent> fillSurveyContent() {
        ArrayList<SurveyButtonContent> surveyContent = new ArrayList<>();
        surveyContent.add(new SurveyButtonContent(R.string.adventures, R.drawable.hobbit));
        surveyContent.add(new SurveyButtonContent(R.string.sci_fi, R.drawable.science));
        surveyContent.add(new SurveyButtonContent(R.string.detective, R.drawable.detective));
        surveyContent.add(new SurveyButtonContent(R.string.fantasy, R.drawable.fantasy));
        surveyContent.add(new SurveyButtonContent(R.string.history, R.drawable.history));
        surveyContent.add(new SurveyButtonContent(R.string.mythic, R.drawable.mythic));
        surveyContent.add(new SurveyButtonContent(R.string.thriller, R.drawable.thriller));
        surveyContent.add(new SurveyButtonContent(R.string.poetry, R.drawable.poetry));
        surveyContent.add(new SurveyButtonContent(R.string.war, R.drawable.war));
        surveyContent.add(new SurveyButtonContent(R.string.children, R.drawable.children));
        surveyContent.add(new SurveyButtonContent(R.string.comix, R.drawable.comix));
        surveyContent.add(new SurveyButtonContent(R.string.tech, R.drawable.tech));
        surveyContent.add(new SurveyButtonContent(R.string.philosophy, R.drawable.philosophy));
        surveyContent.add(new SurveyButtonContent(R.string.sci_pop, R.drawable.scipop));
        surveyContent.add(new SurveyButtonContent(R.string.sci_pop, R.drawable.scipop));
        return surveyContent;
    }
}
