package com.application.spokn;

/**
 * Created by Javi G on 2/8/2017.
 */

public class Challenge {

    private String c_name;  //challenge name
    private String c_sdescription; //short challenge description (for cards on card menu)
    private String c_ldescription;  //complete challenge description (for challenge main page)
    private int c_image;    //album index image to be used for challenge card

    public Challenge(){}

    public Challenge(String c_name, String c_sdescription, String c_ldescription, int c_image){

        this.c_name=c_name;
        this.c_sdescription=c_sdescription;
        this.c_ldescription=c_ldescription;
        this.c_image=c_image;

    }

    public void SetName(String c_name){
        this.c_name=c_name;
    }
    public void SetLongDescription(String c_ldescription) { this.c_ldescription=c_ldescription;}
    public void SetShortDescription(String c_sdescription){
        this.c_sdescription=c_sdescription;
    }
    public void SetImage(int image){
        this.c_image=image;
    }

    public String GetName(){ return c_name; }
    public String GetShortDescription(){
        return c_sdescription;
    }
    public String GetLongDescription(){ return c_ldescription;}
    public int GetImage(){
        return c_image;
    }

}
