package com.application.spokn;

/**
 * Created by kranthi on 2/20/2017.
 *
 * THis Package is used to store all the global variable values.
 */

public class Global {

    static String User = null;           //Used to store Application User's Name
    static String Password = null;       //Used to store Application User's Password
    static String FName = null;          //Used to store Application User's First Name
    static String LName = null;          //Used to store Application User's Last Name
    static String ERROR="";                     //Used to store Application Errors and to access from other packages

    public static String getWatsonUserID() {
        return WatsonUserID;
    }

    public static String getWatsonPassword() {
        return WatsonPassword;
    }

    static final String WatsonUserID = "e8531d98-06ed-454b-b534-34515fcb1ae2";
    static final String WatsonPassword = "RIealsZATpzx";

    public static String getUser() {
        return User;
    }

    public static void setUser(String user) {
        User = user;
    }

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    public static String getFName() {
        return FName;
    }

    public static void setFName(String FName) {
        Global.FName = FName;
    }

    public static String getLName() {
        return LName;
    }

    public static void setLName(String LName) {
        Global.LName = LName;
    }

    public static String getERROR() {
        return ERROR;
    }

    public static void setERROR(String ERROR) {
        Global.ERROR = ERROR;
    }
}
