package com.example.foodstadium;

public class Item {
    private int id;
    private int votes;
    private String name;
    private boolean selected;
    private String category;

    public Item(String name){
        this.votes = 0;
        this.name = name;
        this.selected = false;
        this.category = "Other";
    }
    public Item(int id,String name, String category){
        this.votes = 0;
        this.name = name;
        this.id = id;
        this.selected = false;
        this.category = category;
    }

    public Item(int id,int votes,String name,boolean selected, String category){
        this.id = id;
        this.votes = votes;
        this.name = name;
        this.selected = selected;
        this.category = category;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public boolean isSelected() {
        return selected;
    }

    public String getCategory(){ return category;}
}
