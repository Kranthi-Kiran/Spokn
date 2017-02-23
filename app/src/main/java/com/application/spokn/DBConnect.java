package com.application.spokn;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kranthi on 2/18/2017.
 */

public class DBConnect {

    private static final String TAG = "DBConnect";
    Connection conn;
    ResultSet result;
    Statement stmt;
    String sql;

    public boolean loginUser(){

        try{
            String link="http://kkalletla.com/LoginUser.php";
            String data  = URLEncoder.encode("username", "UTF-8") + "=" +
                    URLEncoder.encode(Global.getUser(), "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(Global.getPassword(), "UTF-8");

            URL url = new URL(link);
            Log.d(TAG,"Connecting URL: "+url);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            String RetResult = sb.toString();
            Log.d(TAG,"Results Tag from Database: "+RetResult);
            //System.out.println(RetResult.substring(RetResult.indexOf("{"), RetResult.lastIndexOf("}") + 1));

            JSONObject jObj = new JSONObject(RetResult.substring(RetResult.indexOf("{"), RetResult.lastIndexOf("}") + 1));

            Global.setFName(jObj.getString("FName"));
            Global.setLName(jObj.getString("LName"));
            Log.d(TAG,"First Name: "+Global.getFName()+"\tLast Name: "+Global.getLName());
            if(!Global.getFName().equals("null") && !Global.getLName().equals("null")){
                return  true;
            }

        } catch(Exception e){
            e.printStackTrace();
            Log.d(TAG,"Error While fetching User from Database");
            Global.setERROR("Please check your Internet Connection.");
        }
        return false;
    }

    public String getCategory(){
        String category="";
        ArrayList<String> categoryList = new ArrayList<String>();

        try{
            String link="http://kkalletla.com/GetCategory.php";
            /*String data  = URLEncoder.encode("category", "UTF-8") + "=" +
                    URLEncoder.encode(Category, "UTF-8");*/

            URL url = new URL(link);
            System.out.println(url);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
/*            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();*/

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            String RetResult = sb.toString();
            System.out.println(RetResult);

            JSONArray jArray = new JSONArray(RetResult);

            for(int i = 0; i < jArray.length(); i++) {
                JSONObject jObj = jArray.getJSONObject(i);
                categoryList.add(jObj.getString("Category"));
            }

            Random random = new Random();
            category = categoryList.get((random.nextInt())%jArray.length());

        } catch(Exception e){
            e.printStackTrace();
            Global.setERROR("Please check your Internet Connection.");
        }


        return category;
    }

    public ArrayList<String> getCategoryKey(String Category){
        ArrayList<String> categoryKeys = new ArrayList<String>();

        try{
            String link="http://kkalletla.com/GetCategoryKeys.php";
            String data  = URLEncoder.encode("category", "UTF-8") + "=" +
                    URLEncoder.encode(Category, "UTF-8");

            URL url = new URL(link);
            System.out.println(url);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            String RetResult = sb.toString();
            System.out.println(RetResult);
            //System.out.println(RetResult.substring(RetResult.indexOf("{"), RetResult.lastIndexOf("}") + 1));

            JSONArray jArray = new JSONArray(RetResult);//.substring(RetResult.indexOf("{"), RetResult.lastIndexOf("}") + 1));

            for(int i = 0; i < jArray.length(); i++) {
                JSONObject jObj = jArray.getJSONObject(i);

                categoryKeys.add(jObj.getString("CategoryKey"));
            }

            //return  true;

        } catch(Exception e){
            e.printStackTrace();
            Global.setERROR("Please check your Internet Connection.");
        }


        return categoryKeys;
    }
}
