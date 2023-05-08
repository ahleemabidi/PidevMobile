/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Calendar;
import static com.codename1.ui.events.ActionEvent.Type.Calendar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Vehicule;
import com.mycompany.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;








/**
 *
 * @author asus
 */
public class ServiceVehicule {
      public ArrayList<Vehicule> vehicule;

    public static ServiceVehicule instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceVehicule() {
        req = new ConnectionRequest();
    }

    public static ServiceVehicule getInstance() {
        if (instance == null) {
            instance = new ServiceVehicule();
        }
        return instance;
    }
       public ArrayList<Vehicule> parseTasks(String jsonText) {
        try {
            vehicule = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                Vehicule v = new Vehicule();

    if (obj.containsKey("id") && obj.get("id") instanceof Number) {
        v.setId((int) Float.parseFloat(obj.get("id").toString()));
    }
    if (obj.containsKey("disponibilite") && obj.get("disponibilite") instanceof Number) {
        v.setDisponibilite((int) Float.parseFloat(obj.get("disponibilite").toString()));
    }
     if (obj.containsKey("numEntretien") && obj.get("numEntretien") instanceof Number) {
        v.setNum_entretien((int) Float.parseFloat(obj.get("numEntretien").toString()));
    }
//     if (obj.containsKey("dateEntretien")) {
//    String dateStr = obj.get("dateEntretien").toString();
//    try {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = dateFormat.parse(dateStr);
//        v.setDate_entretien(date);
//    } catch (ParseException e) {
//        e.printStackTrace();
//    }
//}
if (obj.containsKey("dateEntretien")) {
    String datetimeStr = obj.get("dateEntretien").toString();
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        Date date = dateFormat.parse(datetimeStr);
        v.setDate_entretien(date);

        // Maintenant vous pouvez afficher la date
        System.out.println("Date d'entretien : " + date);
    } catch (ParseException e) {
        e.printStackTrace();
    }
}
     
                v.setRes_entretien(obj.get("resEntretien").toString());
               vehicule.add(v);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return vehicule;
    }
       public ArrayList<Vehicule> getAllVehicule() {
        String url = Statics.BASE_URL + "afficherV";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                vehicule = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return vehicule;
    }
       
         public boolean addVehicule(Vehicule v,Picker dateEntretienPicker) {

        int disponibilite = v.getDisponibilite();
        int numEntretien = v.getNum_entretien();
    Date dateEntretien = dateEntretienPicker.getDate();
String resEntretien = v.getRes_entretien();
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String formattedDate = dateFormat.format(dateEntretien);
formattedDate = Util.encodeUrl(formattedDate);

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "ajoutV/?disponibilite=" + disponibilite + "&numEntretien=" + numEntretien + "&dateEntretien=" +formattedDate  + "&resEntretien=" +resEntretien ;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         public boolean deleteVehicule(int id) {

    String url = Statics.BASE_URL + "deleteV/" + id;
    req.setUrl(url);
    req.setPost(false);

       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
}
             
          public boolean updateVehicule(int disponibilite, int numEntretien,Date dateEntretien , String resEntretien,int id) {

        String url = Statics.BASE_URL + "updateV/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("disponibilite", String.valueOf(disponibilite));
        req.addArgument("numEntretien", String.valueOf(numEntretien));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String formattedDate = dateFormat.format(dateEntretien);
req.addArgument("dateEntretien", formattedDate);
        req.addArgument("resEntretien", resEntretien);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
          
    }

