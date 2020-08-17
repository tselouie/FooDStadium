package com.example.foodstadium;

import java.util.Date;

public class HistoryItem {
    private String category;
    private String name;
    private Date timeStamp;

    public HistoryItem(String name, String category){
        this.name = name;
        this.category = category;
        this.timeStamp = new Date();
    }

    public HistoryItem(String name, String category, Date timeStamp){
        this.name = name;
        this.category = category;
        this.timeStamp = timeStamp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeStamp() {
        return timeStamp.toString();
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
