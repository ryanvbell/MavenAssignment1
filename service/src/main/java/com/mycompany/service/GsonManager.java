/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author w200843582
 */
public class GsonManager {
        
    public String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    
    public void JsonParser() throws Exception{
        String json = readUrl("https://www.w3schools.com/angular/customers.php");
        
        Gson gson = new Gson();
        
        Record record = gson.fromJson(json, Record.class);
        System.out.println("Restaurants:\n");
        for (User user : record.records){
            System.out.println("Name: "+user.Name+"\n"+"Location: "+user.City+", "+user.Country+"\n");
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        GsonManager gm = new GsonManager();
        gm.JsonParser();
    }
}
