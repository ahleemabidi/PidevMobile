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
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Mission;
import com.mycompany.entities.Vehicule;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServiceMission {

    public ArrayList<Mission> mission;

    public static ServiceMission instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceMission() {
        req = new ConnectionRequest();
    }

    public static ServiceMission getInstance() {
        if (instance == null) {
            instance = new ServiceMission();
        }
        return instance;
    }

    public ArrayList<Mission> parseTasks(String jsonText) {
        try {
            mission = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Mission m = new Mission();

                if (obj.containsKey("idMission") && obj.get("idMission") instanceof Number) {
                    m.setId_mission((int) Float.parseFloat(obj.get("idMission").toString()));
                }
                m.setMatricule(obj.get("matricule").toString());
                m.setDescription(obj.get("description").toString());

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
                if (obj.containsKey("heureDebut")) {
                    String datetimeStr = obj.get("heureDebut").toString();
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                        Date date = dateFormat.parse(datetimeStr);
                        m.setHeure_debut(date);

                        // Maintenant vous pouvez afficher la date
                        System.out.println("heureDebut : " + date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (obj.containsKey("heureFin")) {
                    String datetimeStrF = obj.get("heureFin").toString();
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                        Date date = dateFormat.parse(datetimeStrF);
                        m.setHeure_fin(date);

                        // Maintenant vous pouvez afficher la date
                        System.out.println("heureFin : " + date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                mission.add(m);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return mission;
    }

    public ArrayList<Mission> getAllMission() {
        String url = Statics.BASE_URL + "afficherM";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                mission = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return mission;
    }

    public boolean addMission(Mission m, Picker heureDebutPicker, Picker heureFinPicker) {

        String matricule = m.getMatricule();
        String description = m.getDescription();
        Date heureDebut = heureDebutPicker.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String formattedDateD = dateFormat.format(heureDebut);
        formattedDateD = Util.encodeUrl(formattedDateD);

        Date heureFin = heureFinPicker.getDate();

        String formattedDateF = dateFormat.format(heureFin);
        formattedDateF = Util.encodeUrl(formattedDateF);

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "ajoutM/?matricule=" + matricule + "&description=" + description + "&heureDebut=" + formattedDateD + "&heureFin=" + formattedDateF;

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
         public boolean deleteMission(int idMission) {

    String url = Statics.BASE_URL + "deleteM/" + idMission;
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
             

    public boolean updateMission(String matricule, String description, Date heureDebut, Date heureFin, int idMission) {

        String url = Statics.BASE_URL + "updateM/" + idMission;
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("matricule", matricule);
        req.addArgument("description", description);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(heureDebut);
        req.addArgument("heureDebut", formattedDate);
        String formattedDateF = dateFormat.format(heureFin);
        req.addArgument("heureFin", formattedDateF);

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
