package com.example.uniscovery;

public class Tags {
    public int ID_tag;
    public String NombreTag;



    public Tags(int IDTag, String Tag) {
        ID_tag=IDTag;
        NombreTag=Tag;
    }
    public Tags() {
       ID_tag=0;
       NombreTag="";
    }
}
