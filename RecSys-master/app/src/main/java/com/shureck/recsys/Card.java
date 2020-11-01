package com.shureck.recsys;

public class Card {
    private int imgID;
    private int typeFlag;
    private String eventName;
    private String eventTime;
    private String eventDescription;

    public Card(int typeFlag, int imgID, String eventName, String eventTime, String eventDescription) {
        this.imgID = imgID;
        this.typeFlag = typeFlag;
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventDescription = eventDescription;
    }

    public int getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(int typeFlag) {
        this.typeFlag = typeFlag;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
